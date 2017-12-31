package com.ecash.ecashcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.model.cms.Permission;
import com.ecash.ecashcore.model.cms.User;
import com.ecash.ecashcore.repository.PermissionRepository;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class PermissionService {
  @Autowired PermissionRepository permissionRepository;
  
  public Iterable<Permission> findAll(Predicate predicate, Pageable pageable) {
    return permissionRepository.findAll(predicate, pageable);
  }

}
