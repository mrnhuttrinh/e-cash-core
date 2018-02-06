package com.ecash.ecashcore.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractHistory<T extends AbstractHistoryType> extends BaseUUID {

  @Column(name = "history_details", nullable = true)
  private String details;

  private String status;

  @Column(name = "created_by", nullable = true)
  private String createdBy;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "history_type", nullable = false)
  private T type;

  public AbstractHistory() {
    super();
  }

  public AbstractHistory(T type) {
    super();
    this.type = type;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public T getType() {
    return type;
  }

  public void setType(T type) {
    this.type = type;
  }
}
