package com.ecash.ecashcore.model;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Table(name="merchants")
public class Merchants extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	private String name;
	
	private String phone;
	
	private String email;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
