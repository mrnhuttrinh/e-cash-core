package com.ecash.ecashcore.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

  /**
   * serialVersionUID
   */
  protected static final long serialVersionUID = 1234567890L;

  @Column(name = "created_at")
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createdAt;

  @Column(name = "updated_at")
  @Temporal(TemporalType.TIMESTAMP)
  protected Date updatedAt;

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

  @PrePersist
  void createdAt() {
    this.createdAt = this.updatedAt = new Date();
  }

  @PreUpdate
  void updatedAt() {
    this.updatedAt = new Date();
  }
}
