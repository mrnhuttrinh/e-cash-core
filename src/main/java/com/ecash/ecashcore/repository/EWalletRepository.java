package com.ecash.ecashcore.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

import com.ecash.ecashcore.model.wallet.EWallet;
import com.ecash.ecashcore.model.wallet.QEWallet;
import com.ecash.ecashcore.util.DateTimeUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

public interface EWalletRepository extends BaseQuerydslRepository<EWallet, String, QEWallet> {
    @Override
    default public void customize(QuerydslBindings bindings, QEWallet e) {
      bindings.bind(e.status).first(new SingleValueBinding<StringPath, String>() {
        @Override
        public Predicate bind(StringPath path, String value) {
          return path.equalsIgnoreCase(value);
        }
      });

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

      bindings.bind(Double.class).all((final NumberPath<Double> path, final Collection<? extends Double> values) -> {
        final List<? extends Double> doubles = new ArrayList<>(values);
        Collections.sort(doubles);
        if (doubles.size() == 2) {
          return path.between(doubles.get(0), doubles.get(1));
        }
        return path.eq(doubles.get(0));
      });
    }

}
