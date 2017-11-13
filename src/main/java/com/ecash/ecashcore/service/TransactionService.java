package com.ecash.ecashcore.service;

import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.enums.TransactionTypeEnum;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.BalanceHistory;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.MerchantTerminal;
import com.ecash.ecashcore.model.Transaction;
import com.ecash.ecashcore.model.TransactionDetail;
import com.ecash.ecashcore.model.TransactionType;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.BalanceHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.MerchantTerminalRepository;
import com.ecash.ecashcore.repository.TransactionDetailRepository;
import com.ecash.ecashcore.repository.TransactionRepository;
import com.ecash.ecashcore.repository.TransactionTypeRepository;
import com.ecash.ecashcore.util.JsonUtil;
import com.ecash.ecashcore.vo.ChargeRequestVO;
import com.ecash.ecashcore.vo.ExtendedInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

  @Autowired
  CardRepository cardRepository;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  BalanceHistoryRepository balanceHistoryRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  TransactionTypeRepository transactionTypeRepository;

  @Autowired
  TransactionDetailRepository transactionDetailRepository;

  @Autowired
  MerchantTerminalRepository merchantTerminalRepository;

  public Transaction chargeRequest(ChargeRequestVO chargeRequest) {
    if (chargeRequest.getCard() == null || chargeRequest.getAmount() == null
        || chargeRequest.getExtendedInformation() == null) {
      throw new InvalidInputException("Required information is missing");
    }

    ExtendedInformationVO extendedInformation = chargeRequest.getExtendedInformation();
    if (extendedInformation.getAdditionalTerminalInfo() == null) {
      throw new InvalidInputException("Required information is missing. Missing terminal information");
    }

    Optional<Card> card = Optional.ofNullable(cardRepository.findByCardCode(chargeRequest.getCard().getNumber()));

    if (!card.isPresent()) {
      throw new ValidationException("Card number is not valid or not exist.");
    } else if (!card.get().getStatus().equals(StatusEnum.ACTIVE.getValue())){
      throw new ValidationException("Card is inactive.");
    }

    Account account = card.get().getAccount();

    if (!account.getStatus().equals(StatusEnum.ACTIVE.getValue())) {
      throw new ValidationException("Account is inactive.");
    }

    double remainAmount = account.getCurrentBalance() - chargeRequest.getAmount();
    if (remainAmount < 0) {
      throw new ValidationException("Account don't have enough money.");
    }

    account.setCurrentBalance(remainAmount);
    accountRepository.save(account);

    Date transactionTime = Calendar.getInstance().getTime();

    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account, account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    TransactionType transactionType = transactionTypeRepository.findOne(TransactionTypeEnum.EXPENSE.getName());
    Transaction transaction = new Transaction(account, transactionType, transactionTime, chargeRequest.getAmount());
    transactionRepository.save(transaction);

    Optional<MerchantTerminal> merchantTerminal = Optional.ofNullable(
        merchantTerminalRepository.findOne(extendedInformation.getAdditionalTerminalInfo().getTerminalId()));

    if (!merchantTerminal.isPresent()) {
      throw new ValidationException("Terminal id is not valid or not exist.");
    }

    TransactionDetail transactionDetail = new TransactionDetail(transaction, extendedInformation.getTypeOfGoods(),
        JsonUtil.objectToJsonString(extendedInformation.getTransactionDetails()), merchantTerminal.get().getMerchant());
    transactionDetailRepository.save(transactionDetail);
    return transaction;
  }
}
