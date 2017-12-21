package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.cms.CardType;

public interface CardTypeRepository extends JpaRepository<CardType, String> {

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(CardType entity);

  CardType findByTypeCode(String typeCode);
}
