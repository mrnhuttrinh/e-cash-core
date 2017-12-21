package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.PlanType;

public interface PlanTypeRepository extends JpaRepository<PlanType, String> {
  
  PlanType findByTypeCode(String typeCode);
}
