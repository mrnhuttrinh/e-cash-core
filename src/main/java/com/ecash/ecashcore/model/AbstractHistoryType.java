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
  private String type;
  private String name;
  private String description;
  private String status;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
