package com.ecash.ecashcore.repository;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.QRole;
import com.ecash.ecashcore.model.cms.Role;

//@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public interface RoleRepository extends BaseQuerydslRepository<Role, String, QRole> {

  Role findByName(String name);
  Role findByNameIgnoreCase(String name);
}
