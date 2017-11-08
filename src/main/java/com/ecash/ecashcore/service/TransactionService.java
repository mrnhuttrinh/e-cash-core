package com.ecash.ecashcore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.TransactionException;
import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.BalanceHistory;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.Transaction;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.BalanceHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.TransactionRepository;
import com.ecash.ecashcore.vo.DeductionVO;

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

  public void deduct(DeductionVO deductionInfo) {

    Optional<Card> card = Optional.ofNullable(cardRepository.findByCardCode(deductionInfo.getCardCode()));

    if (!card.isPresent()) {
      throw new InvalidInputException("Card code is not valid or not exist!");
    }

    Account account = card.get().getAccount();

    double remainAmount = account.getCurrentBalance() - deductionInfo.getAmount();
    if (remainAmount < 0) {
      throw new TransactionException("Account don't have enough money!");
    }

    account.setCurrentBalance(remainAmount);
    accountRepository.save(account);

    BalanceHistory balanceHistory = new BalanceHistory(deductionInfo.getTime(), account, account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);
    
    Transaction transaction = new Transaction();
  }
}
