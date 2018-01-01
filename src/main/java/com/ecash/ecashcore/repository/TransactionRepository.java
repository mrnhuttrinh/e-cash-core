package com.ecash.ecashcore.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.QTransaction;
import com.ecash.ecashcore.model.cms.Transaction;
import com.ecash.ecashcore.util.DateTimeUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public interface TransactionRepository
    extends BaseQuerydslRepository<Transaction, String, QTransaction>
{

  @Query("SELECT o FROM Transaction o WHERE o.relatedTransaction.id = :transactionId")
  List<Transaction> findByRelatedTransactionId(@Param("transactionId") String transactionId);

  public List<Transaction> findByDateBetweenAndCardIsNotNull(Date fromDate, Date toDate);
  
}
