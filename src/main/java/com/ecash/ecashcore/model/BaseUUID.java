package com.ecash.ecashcore.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseUUID extends BaseModel {

  /**
   * serialVersionUID
   */
  protected static final long serialVersionUID = BaseModel.serialVersionUID;

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  protected String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
