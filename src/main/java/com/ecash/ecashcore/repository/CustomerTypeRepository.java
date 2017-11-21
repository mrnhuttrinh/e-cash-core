package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.CustomerType;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, String> {

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(CustomerType entity);
}