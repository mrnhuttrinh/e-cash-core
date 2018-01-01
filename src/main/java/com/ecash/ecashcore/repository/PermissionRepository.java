package com.ecash.ecashcore.repository;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Permission;
import com.ecash.ecashcore.model.cms.QPermission;

@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public interface PermissionRepository extends BaseQuerydslRepository<Permission, String, QPermission> {

  Permission findByName(String name);

}
