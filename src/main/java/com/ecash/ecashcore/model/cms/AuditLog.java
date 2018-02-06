package com.ecash.ecashcore.model.cms;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseUUID;

@Entity
@Table(name = "audit_log")
public class AuditLog extends BaseUUID {

  private String type;

  private String category;

  private String status;

  private String parameter;

  private String result;

  public AuditLog() {
    super();
  }

  public AuditLog(String type, String category, String status, String parameter, String result) {
    super();
    this.type = type;
    this.category = category;
    this.status = status;
    this.parameter = parameter;
    this.result = result;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getParameter() {
    return parameter;
  }

  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
