package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.cms.AccountType;
import com.ecash.ecashcore.model.cms.QAccountType;

public interface AccountTypeRepository extends BaseQuerydslRepository<AccountType, String, QAccountType>  {

  @Override
  @RestResource(exported = false)
  void delete(String id);

  @Override
  @RestResource(exported = false)
  void delete(AccountType entity);

  @Override
  @RestResource(exported = false)
  void delete(Iterable<? extends AccountType> entities);

  @Override
  @RestResource(exported = false)
  void deleteAll();

  @Override
  @RestResource(exported = false)
  <S extends AccountType> S save(S entity);

  @Override
  @RestResource(exported = false)
  <S extends AccountType> List<S> save(Iterable<S> entities);

  AccountType findByTypeCode(String typeCode);
}
