package com.ecash.ecashcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.cms.Role;
import com.ecash.ecashcore.repository.RoleRepository;
import com.querydsl.core.types.Predicate;

@Service
public class RoleService {

  @Autowired
  RoleRepository roleRepository;

  public Role getByName(String name) {
    return roleRepository.findByName(name);
  }

  public Iterable<Role> findAll(Predicate predicate, Pageable pageable) {
    return roleRepository.findAll(predicate, pageable);
  }

  public Role getRoleUSER() {
    return getByName(Role.ROLE_USER);
  }
}
