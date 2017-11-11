package com.ecash.ecashcore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "currency_code")
public class CurrencyCode extends BaseModel {

  @Id
  @Column(name = "code", nullable = false)
  private String code;

  @Column(name = "name", nullable = true)
  private String text;

  @Column(name = "created_at", nullable = true)
  private Date createdAt;

  @Column(name = "updated_at", nullable = true)
  private Date updatedAt;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
