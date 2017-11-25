package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.TransactionDetail;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, String> {

  @Query("SELECT o FROM TransactionDetail o WHERE o.transactionDetailId.transaction.id = :transactionId")
  TransactionDetail findByTransactionId(@Param("transactionId") String transactionId);
}
