package com.ecash.ecashcore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.QUser;
import com.ecash.ecashcore.model.cms.User;
import com.querydsl.core.types.Predicate;

public interface UserRepository extends BaseQuerydslRepository<User, String, QUser> {
  User findByEmail(String email);

  User findByUsername(String username);
  
  @Override
  @PreAuthorize(value = "hasPermission(null, 'USER_LIST/VIEW')")
  public Page<User> findAll(Predicate predicate, Pageable pageable);
  
  @RestResource(exported = true)
  Page<User> findAll(Pageable pageable);
}
