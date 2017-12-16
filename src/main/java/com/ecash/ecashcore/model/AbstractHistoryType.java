package com.ecash.ecashcore.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractHistoryType extends BaseModel {
  public static String UPDATED = "UPDATED";
  public static String DELETED = "DELETED";
  public static String CREATED = "CREATED";
  public static String LOCKED = "LOCKED";
  public static String UNLOCKED = "UNLOCKED";
  @Id
  private String typeCode;
  private String name;
  private String description;
  private String status;

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
