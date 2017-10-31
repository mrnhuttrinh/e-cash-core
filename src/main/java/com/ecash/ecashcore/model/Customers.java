package com.ecash.ecashcore.model;

import java.util.Date;

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
@Table(name="customers")
public class Customers extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="type_code", nullable=true)
	private CustomerTypes typeCode;
	
	@Column(name="cif_number")
	private Integer cifNumber;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	private String gender;
	
	@Column(name="identify_number")
	private String identifyNumber;
	
	@Column(name="date_became_customer")
	private Date dateBecameCustomer;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomerTypes getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(CustomerTypes typeCode) {
		this.typeCode = typeCode;
	}

	public Integer getCifNumber() {
		return cifNumber;
	}

	public void setCifNumber(Integer cifNumber) {
		this.cifNumber = cifNumber;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdentifyNumber() {
		return identifyNumber;
	}

	public void setIdentifyNumber(String identifyNumber) {
		this.identifyNumber = identifyNumber;
	}

	public Date getDateBecameCustomer() {
		return dateBecameCustomer;
	}

	public void setDateBecameCustomer(Date dateBecameCustomer) {
		this.dateBecameCustomer = dateBecameCustomer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
