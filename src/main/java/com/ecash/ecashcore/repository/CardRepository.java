package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.QCard;
import com.ecash.ecashcore.util.DateTimeUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
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

public interface CardRepository extends JpaRepository<Card, String>, QueryDslPredicateExecutor<Card>, QuerydslBinderCustomizer<QCard> {

  @Override
  default public void customize(QuerydslBindings bindings, QCard card) {
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
      
      // Expressions "to_char" only work in postgresql
      return Expressions.stringTemplate("to_char({0}, {1})", path, DateTimeUtils.DEFAULT_FORMAT)
          .likeIgnoreCase(DateTimeUtils.toDefaultFormatString(dates.get(0)));
    });
  }
  
  Card findByCardCode(String cardCode);
}
