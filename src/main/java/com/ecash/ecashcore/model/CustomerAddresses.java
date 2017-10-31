package com.ecash.ecashcore.model;

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
@Table(name="customer_addresses")
public class CustomerAddresses extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id", nullable=true)
	private Customers customerId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="address_id", nullable=true)
	private Addresses addressId;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customers getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Customers customerId) {
		this.customerId = customerId;
	}

	public Addresses getAddressId() {
		return addressId;
	}

	public void setAddressId(Addresses addressId) {
		this.addressId = addressId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
