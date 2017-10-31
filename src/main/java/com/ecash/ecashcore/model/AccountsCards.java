package com.ecash.ecashcore.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="accounts_cards")
public class AccountsCards extends BaseModel {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="account_id", nullable=true)
	private Accounts accountId;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="card_id", nullable=true)
	private Cards cardId;
	
	private String status;

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

	public Cards getCardId() {
		return cardId;
	}

	public void setCardId(Cards cardId) {
		this.cardId = cardId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
