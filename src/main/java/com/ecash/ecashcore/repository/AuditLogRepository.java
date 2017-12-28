package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.AuditLog;
import com.ecash.ecashcore.model.cms.QAuditLog;

public interface AuditLogRepository extends BaseQuerydslRepository<AuditLog, String, QAuditLog> {

}
