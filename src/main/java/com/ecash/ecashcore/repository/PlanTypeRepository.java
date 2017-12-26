package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.PlanType;
import com.ecash.ecashcore.model.cms.QPlanType;

public interface PlanTypeRepository extends BaseQuerydslRepository<PlanType, String, QPlanType> {

  PlanType findByTypeCode(String typeCode);
}
