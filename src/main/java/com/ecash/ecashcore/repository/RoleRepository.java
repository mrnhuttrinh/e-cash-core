package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.Role;

@RestResource(exported = false)
public interface RoleRepository extends JpaRepository<Role, String>, QueryDslPredicateExecutor<Role> {

  Role findByName(String name);

}
