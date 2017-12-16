package com.ecash.ecashcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.cms.Role;
import com.ecash.ecashcore.repository.RoleRepository;

@Service
public class RoleService {
  @Autowired RoleRepository roleRepository;
  
  public Role getByName(String name) {
    return roleRepository.findByName(name);
  }
}
