package com.ecash.ecashcore.vo;

import java.util.Date;

import com.ecash.ecashcore.model.cms.Card;

public class CardVO {
  private String cardNumber;

  private String status;

  private String cardCode;

  private Date effectiveDate;

  private Date expiryDate;

  public CardVO() {
    super();
  }

  public CardVO(Card card) {
    super();

    this.cardNumber = card.getCardNumber();
    this.status = card.getStatus();
    this.cardCode = card.getCardCode();
    this.effectiveDate = card.getEffectiveDate();
    this.expiryDate = card.getEffectiveDate();
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCardCode() {
    return cardCode;
  }

  public void setCardCode(String cardCode) {
    this.cardCode = cardCode;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
}
