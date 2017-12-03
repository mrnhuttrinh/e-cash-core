package com.ecash.ecashcore.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.QTransaction;
import com.ecash.ecashcore.model.Transaction;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;

public interface TransactionRepository extends JpaRepository<Transaction, String>, QueryDslPredicateExecutor<Transaction>, QuerydslBinderCustomizer<QTransaction> {

  @Query("SELECT o FROM Transaction o WHERE o.relatedTransaction.id = :transactionId")
  List<Transaction> findByRelatedTransactionId(@Param("transactionId") String transactionId);
  
  @Override
  default public void customize(QuerydslBindings bindings, QTransaction transaction) {
    bindings.bind(String.class).first(new SingleValueBinding<StringPath, String>() {
      @Override
      public Predicate bind(StringPath path, String value) {
        return path.containsIgnoreCase(value);
      }
    });

    bindings.bind(Date.class).all((final DateTimePath<Date> path, final Collection<? extends Date> values) -> {
      final List<? extends Date> dates = new ArrayList<>(values);
      Collections.sort(dates);
      if (dates.size() == 2) {
        return path.between(dates.get(0), dates.get(1));
      }
      return path.eq(dates.get(0));
      // throw new IllegalArgumentException("2 date params(from & to) expected for:" +
      // path + " found:" + values);
    });
  }
}
