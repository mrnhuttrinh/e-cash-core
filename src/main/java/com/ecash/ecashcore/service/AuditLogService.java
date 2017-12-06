package com.ecash.ecashcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.AuditLog;
import com.ecash.ecashcore.repository.AuditLogRepository;
import com.querydsl.core.types.Predicate;

@Service
public class AuditLogService {

  @Autowired
  AuditLogRepository auditLogRepository;

  public Page<AuditLog> findAll(Predicate predicate, Pageable pageable) {
    return auditLogRepository.findAll(predicate, pageable);
  }

  public AuditLog save(AuditLog auditLog) {
    return auditLogRepository.save(auditLog);
  }
}
