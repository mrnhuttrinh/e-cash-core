package com.ecash.ecashcore.repository;

import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.cms.QRole;
import com.ecash.ecashcore.model.cms.Role;

@RestResource(exported = false)
public interface RoleRepository extends BaseQuerydslRepository<Role, String, QRole> {

  Role findByName(String name);

}
