package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecash.ecashcore.model.cms.Plan;

public interface PlanRepository extends JpaRepository<Plan, String> {

  @Query(value = "SELECT p FROM Plan p where p.planType.typeCode = :planType")
  Plan findbyPlanType(@Param("planType") String planType);
}
