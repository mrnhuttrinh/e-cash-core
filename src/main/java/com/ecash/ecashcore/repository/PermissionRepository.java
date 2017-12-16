package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {

  Permission findByName(String name);

}
