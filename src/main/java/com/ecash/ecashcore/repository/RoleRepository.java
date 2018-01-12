package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.QRole;
import com.ecash.ecashcore.model.cms.Role;

public interface RoleRepository extends BaseQuerydslRepository<Role, String, QRole> {

  Role findByName(String name);
  Role findByNameIgnoreCase(String name);
}
