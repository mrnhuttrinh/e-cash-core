package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "discount_by_time")
public class DiscountByTime extends BaseModel {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column(name = "start_date")
  private Date startDate;

  @Column(name = "end_date")
  private Date endDate;

  @Column(name = "discount_rate")
  private Double discountRate;

  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Double getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(Double discountRate) {
    this.discountRate = discountRate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
