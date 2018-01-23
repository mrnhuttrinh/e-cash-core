package com.ecash.ecashcore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.QCard;
import com.querydsl.core.types.Predicate;

public interface CardRepository extends BaseQuerydslRepository<Card, String, QCard> {

  Card findByCardCode(String cardCode);

  Card findByCardNumber(String cardNumber);
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'CARD_LIST/VIEW')")
  public Page<Card> findAll(Predicate predicate, Pageable pageable);
}
