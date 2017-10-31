package com.ecash.ecashcore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="merchant_discount_by_tier")
public class MerchantDiscountByTier extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="merchant_id", nullable=true)
	private Merchants merchantId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="discount_id", nullable=true)
	private MerchantDiscountByTier discountId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Merchants getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Merchants merchantId) {
		this.merchantId = merchantId;
	}

	public MerchantDiscountByTier getDiscountId() {
		return discountId;
	}

	public void setDiscountId(MerchantDiscountByTier discountId) {
		this.discountId = discountId;
	}
}
