package com.ecash.ecashcore.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.QTransaction;
import com.ecash.ecashcore.model.cms.Transaction;
import com.querydsl.core.types.Predicate;

public interface TransactionRepository
    extends BaseQuerydslRepository<Transaction, String, QTransaction>
{

  @Query("SELECT o FROM Transaction o WHERE o.relatedTransaction.id = :transactionId")
  List<Transaction> findByRelatedTransactionId(@Param("transactionId") String transactionId);

  public List<Transaction> findByDateBetweenAndCardIsNotNull(Date fromDate, Date toDate);
  
  public List<Transaction> findByDateBetween(Date fromDate, Date toDate);
  
  public List<Transaction> findByDateBetweenAndAccount(Date fromDate, Date toDate, Account account);
  
  public List<Transaction> findByDateBetweenAndCard(Date fromDate, Date toDate, Card card);
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'TRANSACTION_LIST/VIEW')")
  public Page<Transaction> findAll(Predicate predicate, Pageable pageable);
  
  @RestResource(exported = true)
  Page<Transaction> findAll(Pageable pageable);
}
