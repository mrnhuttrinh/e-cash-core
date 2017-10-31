package com.ecash.ecashcore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="discount_by_tier")
public class DiscountByTier extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(name="discount_rate")
	private Double discountRate;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
