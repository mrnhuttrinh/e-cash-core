package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.QTransaction;
import com.ecash.ecashcore.model.cms.Transaction;

public interface TransactionRepository
    extends BaseQuerydslRepository<Transaction, String, QTransaction>
{

  @Query("SELECT o FROM Transaction o WHERE o.relatedTransaction.id = :transactionId")
  List<Transaction> findByRelatedTransactionId(@Param("transactionId") String transactionId);

  public List<Transaction> findByDateBetweenAndCardIsNotNull(Date fromDate, Date toDate);
  
  public List<Transaction> findByDateBetween(Date fromDate, Date toDate);
  
  public List<Transaction> findByDateBetweenAndAccount(Date fromDate, Date toDate, Account account);
  
  public List<Transaction> findByDateBetweenAndCard(Date fromDate, Date toDate, Card card);
  
}
