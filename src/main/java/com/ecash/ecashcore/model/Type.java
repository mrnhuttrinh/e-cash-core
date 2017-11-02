package com.ecash.ecashcore.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Type extends BaseModel {

  @Column(name = "type_code", nullable = false)
  protected String typeCode;

  @Column(name = "description", nullable = true)
  protected String description;

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
