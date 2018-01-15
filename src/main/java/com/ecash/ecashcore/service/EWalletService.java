package com.ecash.ecashcore.service;

import com.ecash.ecashcore.enums.EWalletTransactionTypeEnum;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.wallet.EBalanceHistory;
import com.ecash.ecashcore.model.wallet.EWallet;
import com.ecash.ecashcore.model.wallet.EWalletTransaction;
import com.ecash.ecashcore.model.wallet.EWalletTransactionType;
import com.ecash.ecashcore.repository.EWalletBalanceHistoryRepository;
import com.ecash.ecashcore.repository.EWalletRepository;
import com.ecash.ecashcore.repository.EWalletTransactionRepository;
import com.ecash.ecashcore.repository.EWalletTransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
public class EWalletService {

  @Autowired
  EWalletTransactionRepository eWalletTransactionRepository;

  @Autowired
  EWalletRepository eWalletRepository;

  @Autowired
  EWalletBalanceHistoryRepository eWalletBalanceHistoryRepository;

  @Autowired
  EWalletTransactionTypeRepository eWalletTransactionTypeRepository;


  public EWalletTransaction createEWalletTransaction(String eWalletID, double amount) {
    EWallet eWallet = eWalletRepository.findOne(eWalletID);
    Date transactionTime = Calendar.getInstance().getTime();
    if (eWallet != null) {
      // Check balance
      if (eWallet.getCurrentBalance() >= amount) {
        // New e wallet balance history
        EBalanceHistory eBalanceHistory = new EBalanceHistory(transactionTime, eWallet, eWallet.getCurrentBalance());
        eWalletBalanceHistoryRepository.save(eBalanceHistory);

        // Update balance value
        eWallet.setCurrentBalance(eWallet.getCurrentBalance() - amount);
        eWalletRepository.save(eWallet);

        // save transaction
        EWalletTransactionType transactionType = eWalletTransactionTypeRepository
            .findOne(EWalletTransactionTypeEnum.EXPENSE.getName());

        EWalletTransaction transaction = new EWalletTransaction(
            eWallet, transactionType, transactionTime, amount, EWalletTransaction.SUCCESS);

        EWalletTransaction successfulTransaction = eWalletTransactionRepository.save(transaction);
        return successfulTransaction;
      } else {
        throw new ValidationException("Wallet don't have enough money.");
      }
    } else {
      throw new ValidationException("Wallet is not found");
    }
  }
}
