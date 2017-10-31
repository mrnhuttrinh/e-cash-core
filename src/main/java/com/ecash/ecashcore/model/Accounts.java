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
@Table(name="accounts")
public class Accounts extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="type_code", nullable=true)
	private AccountTypes typeCode;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customer_id", nullable=true)
	private Customers customersId;
	
	@Column(name="account_name")
	private String accountName;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="currency_code", nullable=true)
	private CurrencyCodes currencyCode;
	
	@Column(name="date_opened")
	private Date dateOpened;
	
	@Column(name="current_balance")
	private Double currentBalance;
	
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public AccountTypes getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(AccountTypes typeCode) {
		this.typeCode = typeCode;
	}

	public Customers getCustomersId() {
		return customersId;
	}

	public void setCustomersId(Customers customersId) {
		this.customersId = customersId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public CurrencyCodes getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(CurrencyCodes currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
