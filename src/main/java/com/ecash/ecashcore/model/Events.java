package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

public class Events extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="account_id", nullable=true)
	private Accounts accountId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="type_code", nullable=true)
	private EventTypes typeCode;
	
	private Date date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Accounts getAccountId() {
		return accountId;
	}

	public void setAccountId(Accounts accountId) {
		this.accountId = accountId;
	}

	public EventTypes getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(EventTypes typeCode) {
		this.typeCode = typeCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
