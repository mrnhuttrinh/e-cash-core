package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.AddressType;
import com.ecash.ecashcore.model.cms.QAddressType;

public interface AddressTypeRepository extends BaseQuerydslRepository<AddressType, String, QAddressType> {
  
  AddressType findByTypeCode(String typeCode);
}
