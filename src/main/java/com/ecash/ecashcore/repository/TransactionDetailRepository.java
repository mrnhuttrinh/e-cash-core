package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.QTransactionDetail;
import com.ecash.ecashcore.model.cms.TransactionDetail;

public interface TransactionDetailRepository
    extends BaseQuerydslRepository<TransactionDetail, String, QTransactionDetail> {

  @Query("SELECT o FROM TransactionDetail o WHERE o.transactionDetailId.transaction.id = :transactionId")
  TransactionDetail findByTransactionId(@Param("transactionId") String transactionId);
}
