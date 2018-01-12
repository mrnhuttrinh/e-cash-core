package com.ecash.ecashcore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.cms.Permission;
import com.ecash.ecashcore.model.cms.Role;
import com.ecash.ecashcore.repository.PermissionRepository;
import com.ecash.ecashcore.repository.RoleRepository;
import com.querydsl.core.types.Predicate;

@Service
public class RoleService {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PermissionRepository permissionRepository;

  public Role getByName(String name) {
    return roleRepository.findByName(name);
  }

  public Iterable<Role> findAll(Predicate predicate, Pageable pageable) {
    return roleRepository.findAll(predicate, pageable);
  }

  public Role getRoleUSER() {
    return getByName(Role.ROLE_USER);
  }

  public Role findById(String id) {
    return roleRepository.findOne(id);
  }

  public Role updateRolePermission(Role role, List<Permission> permissions) {
    role.setPermissions(permissions);
    return roleRepository.save(role);
  }
  
  public Role addNewRole(String roleName) throws Exception {
    Role roleExistence = roleRepository.findByNameIgnoreCase(roleName);
    if (roleExistence != null) {
      throw new Exception("Role name is exists");
    }
    Role newRole = new Role(roleName);
    return roleRepository.save(newRole);
  }
}
