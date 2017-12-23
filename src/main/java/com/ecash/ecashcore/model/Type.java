package com.ecash.ecashcore.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Type extends BaseModel {

  @Id
  @Column(name = "type_code", nullable = false, unique = true)
  protected String typeCode;

  @Column(name = "description", nullable = true)
  protected String description;

  public Type() {
    super();
  }

  public Type(String typeCode) {
    super();
    this.typeCode = typeCode;
  }

  public Type(String typeCode, String description) {
    super();
    this.typeCode = typeCode;
    this.description = description;
  }

  public String getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(String typeCode) {
    this.typeCode = typeCode;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
