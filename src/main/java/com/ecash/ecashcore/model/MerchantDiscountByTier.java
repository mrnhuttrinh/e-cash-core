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
@Table(name = "merchant_discount_by_tier")
public class MerchantDiscountByTier extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id", nullable = true)
  private Merchant merchantId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discount_id", nullable = true)
  private MerchantDiscountByTier discountId;

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

  public MerchantDiscountByTier getDiscountId() {
    return discountId;
  }

  public void setDiscountId(MerchantDiscountByTier discountId) {
    this.discountId = discountId;
  }
}
