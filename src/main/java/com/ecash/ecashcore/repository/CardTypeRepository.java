package com.ecash.ecashcore.repository;

import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.cms.CardType;
import com.ecash.ecashcore.model.cms.QCardType;

public interface CardTypeRepository extends BaseQuerydslRepository<CardType, String, QCardType> {

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(CardType entity);

  CardType findByTypeCode(String typeCode);
}
