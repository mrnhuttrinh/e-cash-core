package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.ecash.ecashcore.model.QUser;
import com.ecash.ecashcore.model.User;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;

public interface UserRepository extends JpaRepository<User, String>, QueryDslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
  User findByEmail(String email);
  
  User findByUsername(String username); 

  @Override
  default public void customize(QuerydslBindings bindings, QUser user) {
    bindings.bind(String.class).first(new SingleValueBinding<StringPath, String>() {
      @Override
      public Predicate bind(StringPath path, String value) {
        return path.containsIgnoreCase(value);
      }
    });
  }
}
