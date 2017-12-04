package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.AuditLog;

@Projection(name = "custom", types = AuditLog.class)
public interface AuditLogExcerpt extends BaseExcerpt {
  
  public String getId();

  public String getType();

  public String getCategory();

  public String getStatus();

  public String getParameter();

  public String getResult();
}
