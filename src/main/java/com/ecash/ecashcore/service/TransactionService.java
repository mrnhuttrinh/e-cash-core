package com.ecash.ecashcore.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.TransactionTypeEnum;
import com.ecash.ecashcore.exception.DataNotFoundException;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.TransactionException;
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

  public void chargeRequest(ChargeRequestVO chargeRequest) {
    if (chargeRequest.getCard() == null || chargeRequest.getAmount() == null
        || chargeRequest.getExtendedInformation() == null) {
      throw new InvalidInputException("Please input card info, amount and transaction info!");
    }

    Optional<Card> card = Optional.ofNullable(cardRepository.findByCardCode(chargeRequest.getCard().getNumber()));

    if (!card.isPresent()) {
      throw new DataNotFoundException("Card number is not valid or not exist!");
    }

    Account account = card.get().getAccount();

    double remainAmount = account.getCurrentBalance() - chargeRequest.getAmount();
    if (remainAmount < 0) {
      throw new TransactionException("Account don't have enough money!");
    }

    account.setCurrentBalance(remainAmount);
    accountRepository.save(account);

    Date transactionTime = Calendar.getInstance().getTime();

    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account, account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    TransactionType transactionType = transactionTypeRepository.findOne(TransactionTypeEnum.PAYMENT.getName());
    Transaction transaction = new Transaction(account, transactionType, transactionTime, chargeRequest.getAmount());
    transactionRepository.save(transaction);

    ExtendedInformationVO extendedInformation = chargeRequest.getExtendedInformation();
    if (extendedInformation.getAdditionalTerminalInfo() == null) {
      throw new InvalidInputException("Please input terminal info!");
    }

    Optional<MerchantTerminal> merchantTerminal = Optional.ofNullable(
        merchantTerminalRepository.findOne(extendedInformation.getAdditionalTerminalInfo().getTerminalId()));

    if (!merchantTerminal.isPresent()) {
      throw new DataNotFoundException("Terminal id is not valid or not exist!");
    }

    TransactionDetail transactionDetail = new TransactionDetail(transaction, extendedInformation.getTypeOfGoods(),
        JsonUtil.objectToJsonString(extendedInformation.getInvoiceDetails()), merchantTerminal.get().getMerchant());
    transactionDetailRepository.save(transactionDetail);
  }
}
