package com.ecash.ecashcore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card extends BaseModel {

  @Id
  @Column(name = "card_number")
  private String cardNumber;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code", nullable = true)
  private CardType typeCode;

  private String status;

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public CardType getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(CardType typeCode) {
    this.typeCode = typeCode;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
