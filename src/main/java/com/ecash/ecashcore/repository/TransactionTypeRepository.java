package com.ecash.ecashcore.repository;

import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.cms.QTransactionType;
import com.ecash.ecashcore.model.cms.TransactionType;

public interface TransactionTypeRepository extends BaseQuerydslRepository<TransactionType, String, QTransactionType> {

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(TransactionType entity);
}
