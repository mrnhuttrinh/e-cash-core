package com.ecash.ecashcore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.QCustomer;
import com.querydsl.core.types.Predicate;

public interface CustomerRepository extends BaseQuerydslRepository<Customer, String, QCustomer> {

  Customer findByScmsMemberCode(String scmsMemberCode);
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'CUSTOMER_LIST/VIEW')")
  public Page<Customer> findAll(Predicate predicate, Pageable pageable);
  
  @RestResource(exported = true)
  Page<Customer> findAll(Pageable pageable);
}
