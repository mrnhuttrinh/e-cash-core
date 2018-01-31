package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.AccountType;
import com.ecash.ecashcore.model.cms.QAccountType;

public interface AccountTypeRepository extends BaseQuerydslRepository<AccountType, String, QAccountType>  {

  AccountType findByTypeCode(String typeCode);
}
