package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.QCustomer;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;

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

    // bindings.bind(String.class).all(new MultiValueBinding<StringPath, String>() {
    // @Override
    // public Predicate bind(StringPath path, Collection<? extends String> values) {
    // BooleanBuilder predicate = new BooleanBuilder();
    // values.forEach(value -> predicate.or(path.containsIgnoreCase(value)));
    // return predicate;
    // }
    // });
  }
}
