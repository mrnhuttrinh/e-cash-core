package com.ecash.ecashcore.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "merchant_discount_by_time")
public class MerchantDiscountByTime extends BaseModel {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "merchant_id", nullable = true)
  private Merchant merchantId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "discount_id", nullable = true)
  private DiscountByTime discountId;

  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Merchant getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Merchant merchantId) {
    this.merchantId = merchantId;
  }

  public DiscountByTime getDiscountId() {
    return discountId;
  }

  public void setDiscountId(DiscountByTime discountId) {
    this.discountId = discountId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
