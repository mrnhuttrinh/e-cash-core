package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.CustomerType;
import com.ecash.ecashcore.model.cms.QCustomerType;

public interface CustomerTypeRepository extends BaseQuerydslRepository<CustomerType, String, QCustomerType> {

  CustomerType findByTypeCode(String typeCode);
}
