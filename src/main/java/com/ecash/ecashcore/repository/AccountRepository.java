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
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.QAccount;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;

@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public interface AccountRepository
    extends JpaRepository<Account, String>, QueryDslPredicateExecutor<Account>, QuerydslBinderCustomizer<QAccount> {

  @Query("SELECT o FROM Account o JOIN o.cards c WHERE c.cardNumber = :cardId AND o.accountType.typeCode = :accountType")
  List<Account> findByCardIdAndAccountType(@Param("cardId") String cardId, @Param("accountType") String accountType);

  @Override
  default public void customize(QuerydslBindings bindings, QAccount account) {
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
