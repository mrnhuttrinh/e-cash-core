package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.QCustomer;
import com.querydsl.core.types.dsl.StringPath;

public interface CustomerRepository
    extends JpaRepository<Customer, String>, QueryDslPredicateExecutor<Customer>, QuerydslBinderCustomizer<QCustomer> {

  @Override
  default public void customize(QuerydslBindings bindings, QCustomer customer) {
    bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
  }
}
