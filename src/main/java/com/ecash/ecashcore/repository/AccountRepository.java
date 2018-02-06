package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.QAccount;
import com.querydsl.core.types.Predicate;

public interface AccountRepository extends BaseQuerydslRepository<Account, String, QAccount> {

  List<Account> findByCustomer(Customer customer);
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'ACCOUNT_LIST/VIEW')")
  public Page<Account> findAll(Predicate predicate, Pageable pageable);
  
  @RestResource(exported = true)
  Page<Account> findAll(Pageable pageable);
}
