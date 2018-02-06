package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.CardType;
import com.ecash.ecashcore.model.cms.QCardType;

public interface CardTypeRepository extends BaseQuerydslRepository<CardType, String, QCardType> {

  CardType findByTypeCode(String typeCode);
}
