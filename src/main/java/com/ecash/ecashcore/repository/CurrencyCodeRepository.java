package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.CurrencyCode;
import com.ecash.ecashcore.model.cms.QCurrencyCode;

public interface CurrencyCodeRepository extends BaseQuerydslRepository<CurrencyCode, String, QCurrencyCode> {

  CurrencyCode findByCode(String code);
}
