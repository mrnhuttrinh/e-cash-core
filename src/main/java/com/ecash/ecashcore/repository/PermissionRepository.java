package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.Permission;
import com.ecash.ecashcore.model.cms.QPermission;

public interface PermissionRepository extends BaseQuerydslRepository<Permission, String, QPermission> {

  Permission findByName(String name);

}
