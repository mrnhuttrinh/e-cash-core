package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

  @Query("SELECT o FROM Transaction o WHERE o.relatedTransaction.id = :transactionId")
  List<Transaction> findByRelatedTransactionId(@Param("transactionId") String transactionId);
}
