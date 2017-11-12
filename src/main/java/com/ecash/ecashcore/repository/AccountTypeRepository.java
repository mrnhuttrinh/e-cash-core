package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, String> {

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(AccountType entity);
}
