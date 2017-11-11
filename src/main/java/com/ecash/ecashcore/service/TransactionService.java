package com.ecash.ecashcore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.TransactionTypeEnum;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.TransactionException;
import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.BalanceHistory;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.Transaction;
import com.ecash.ecashcore.model.TransactionDetail;
import com.ecash.ecashcore.model.TransactionType;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.BalanceHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.TransactionDetailRepository;
import com.ecash.ecashcore.repository.TransactionRepository;
import com.ecash.ecashcore.repository.TransactionTypeRepository;
import com.ecash.ecashcore.vo.PaymentVO;

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

  public void pay(PaymentVO paymentInfo) {

    Optional<Card> card = Optional.ofNullable(cardRepository.findByCardCode(paymentInfo.getCardCode()));

    if (!card.isPresent()) {
      throw new InvalidInputException("Card code is not valid or not exist!");
    }

    Account account = card.get().getAccount();

    double remainAmount = account.getCurrentBalance() - paymentInfo.getAmount();
    if (remainAmount < 0) {
      throw new TransactionException("Account don't have enough money!");
    }

    account.setCurrentBalance(remainAmount);
    accountRepository.save(account);

    BalanceHistory balanceHistory = new BalanceHistory(paymentInfo.getTime(), account, account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    TransactionType transactionType = transactionTypeRepository.findOne(TransactionTypeEnum.PAYMENT.getName());
    Transaction transaction = new Transaction(account, transactionType, paymentInfo.getTime(),
        account.getCurrencyCode(), paymentInfo.getAmount());
    transactionRepository.save(transaction);

    TransactionDetail transactionDetail = new TransactionDetail(transaction, paymentInfo.getDetail(), null);
    transactionDetailRepository.save(transactionDetail);
  }
}
