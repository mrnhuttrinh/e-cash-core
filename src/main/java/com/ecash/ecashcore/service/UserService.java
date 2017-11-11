package com.ecash.ecashcore.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.model.Permission;
import com.ecash.ecashcore.model.Role;
import com.ecash.ecashcore.model.User;
import com.ecash.ecashcore.repository.PermissionRepository;
import com.ecash.ecashcore.repository.RoleRepository;
import com.ecash.ecashcore.repository.UserRepository;

@Service
@Transactional
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  PermissionRepository permissionRepository;

  public User getById(String id) {
    return userRepository.getOne(id);
  }

  public User getByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public User addNewUser(String email) {

    Permission readPrivilege = this.createPrivilegeIfNotFound("READ");
    Permission writePrivilege = this.createPrivilegeIfNotFound("WRITE");
    Permission fullControlPrivilege = this.createPrivilegeIfNotFound("FULL_CONTROL");
    List<Permission> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, fullControlPrivilege);

    this.createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);

    Role adminRole = roleRepository.findByName("ROLE_ADMIN");
    User user = new User();
    user.setFirstName("Test");
    user.setLastName("Test");
    user.setPassword("passwordEncoder.encode(test)");
    user.setEmail("test@test.com");
    user.setRoles(Arrays.asList(adminRole));
    user.setEnabled(true);
    return userRepository.save(user);
  }

  private Permission createPrivilegeIfNotFound(String name) {
    Permission privilege = permissionRepository.findByName(name);
    if (privilege == null) {
      privilege = new Permission(name);
      permissionRepository.save(privilege);
    }
    return privilege;
  }

  private Role createRoleIfNotFound(String name, Collection<Permission> privileges) {
    Role role = roleRepository.findByName(name);
    if (role == null) {
      role = new Role(name);
      role.setPermissions(privileges);
      roleRepository.save(role);
    }
    return role;
  }
}
