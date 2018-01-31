package com.ecash.ecashcore.model.cms;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseModel;

@Entity
@Table(name = "history_type")
public class HistoryType extends BaseModel {

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
