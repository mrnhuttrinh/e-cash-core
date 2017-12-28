package com.ecash.ecashcore.repository;

import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.cms.CurrencyCode;
import com.ecash.ecashcore.model.cms.QCurrencyCode;

public interface CurrencyCodeRepository extends BaseQuerydslRepository<CurrencyCode, String, QCurrencyCode> {

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(CurrencyCode entity);

  @RestResource(exported = false)
  CurrencyCode findByCode(String code);
}
