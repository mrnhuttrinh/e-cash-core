package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.QCustomer;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public interface CustomerRepository
    extends JpaRepository<Customer, String>, QueryDslPredicateExecutor<Customer>, QuerydslBinderCustomizer<QCustomer> {

  @Override
  default public void customize(QuerydslBindings bindings, QCustomer customer) {
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
