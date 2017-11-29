package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, String> {

}
