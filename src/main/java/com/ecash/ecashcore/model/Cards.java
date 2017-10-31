package com.ecash.ecashcore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cards")
public class Cards extends BaseModel {
	@Id
	@Column(name="card_number")
	private String cardNumber;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="type_code", nullable=true)
	private CardTypes typeCode;

	private String status;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public CardTypes getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(CardTypes typeCode) {
		this.typeCode = typeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
