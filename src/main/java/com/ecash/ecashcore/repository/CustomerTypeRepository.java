package com.ecash.ecashcore.repository;

import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.cms.CustomerType;
import com.ecash.ecashcore.model.cms.QCustomerType;

public interface CustomerTypeRepository extends BaseQuerydslRepository<CustomerType, String, QCustomerType> {

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(CustomerType entity);

  CustomerType findByTypeCode(String typeCode);
}
