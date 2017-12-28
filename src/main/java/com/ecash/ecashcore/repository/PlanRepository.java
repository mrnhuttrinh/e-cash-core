package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.Plan;
import com.ecash.ecashcore.model.cms.QPlan;

public interface PlanRepository extends BaseQuerydslRepository<Plan, String, QPlan> {

  @Query(value = "SELECT p FROM Plan p where p.planType.typeCode = :planType")
  Plan findbyPlanType(@Param("planType") String planType);
}
