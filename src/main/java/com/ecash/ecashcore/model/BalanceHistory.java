package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="balance_history")
public class BalanceHistory extends BaseModel {
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	private Date date;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="account_id", nullable=true)
	private Accounts accountId;
	
	private Double balance;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Accounts getAccountId() {
		return accountId;
	}

	public void setAccountId(Accounts accountId) {
		this.accountId = accountId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
