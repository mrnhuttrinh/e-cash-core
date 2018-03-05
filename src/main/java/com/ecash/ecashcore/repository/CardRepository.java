package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.QCard;
import com.querydsl.core.types.Predicate;

public interface CardRepository extends BaseQuerydslRepository<Card, String, QCard> {

  List<Card> findByCardCode(String cardCode);
  
  List<Card> findByCardCodeAndStatus(String cardCode, String status);

  Card findByCardNumber(String cardNumber);
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'CARD_LIST/VIEW')")
  public Page<Card> findAll(Predicate predicate, Pageable pageable);
  
  @RestResource(exported = true)
  Page<Card> findAll(Pageable pageable);
}
