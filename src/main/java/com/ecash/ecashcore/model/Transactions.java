package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="transactions")
public class Transactions extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="account_id", nullable=true)
	private Accounts accountId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="related_transaction_id", nullable=true)
	private Transactions relatedTransactionId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="type_code", nullable=true)
	private TransactionTypes typeCode;
	
	private Date date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="currency_code", nullable=true)
	private CurrencyCodes currencyCode;
	
	private Double amount;

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

	public Transactions getRelatedTransactionId() {
		return relatedTransactionId;
	}

	public void setRelatedTransactionId(Transactions relatedTransactionId) {
		this.relatedTransactionId = relatedTransactionId;
	}

	public TransactionTypes getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(TransactionTypes typeCode) {
		this.typeCode = typeCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CurrencyCodes getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(CurrencyCodes currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
