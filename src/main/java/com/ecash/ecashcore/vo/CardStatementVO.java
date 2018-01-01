package com.ecash.ecashcore.vo;

import java.util.Date;

public class CardStatementVO
{
  private String id;

  private String card_number;

  private String company;

  private String currencyCode;

  private Date dueDate;

  private Double currentAmount;

  private Double paymentAmount;

  private String createdBy;

  private CardStatementDetailVO cardStatementDetailVO;

  public CardStatementVO()
  {
    super();
  }

  public CardStatementDetailVO getCardStatementDetailVO()
  {
    return cardStatementDetailVO;
  }

  public void setCardStatementDetailVO(CardStatementDetailVO cardStatementDetailVO)
  {
    this.cardStatementDetailVO = cardStatementDetailVO;
  }

  public String getCompany()
  {
    return company;
  }

  public void setCompany(String company)
  {
    this.company = company;
  }

  public String getCurrencyCode()
  {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode)
  {
    this.currencyCode = currencyCode;
  }

  public Date getDueDate()
  {
    return dueDate;
  }

  public void setDueDate(Date dueDate)
  {
    this.dueDate = dueDate;
  }

  public Double getCurrentAmount()
  {
    return currentAmount;
  }

  public void setCurrentAmount(Double currentAmount)
  {
    this.currentAmount = currentAmount;
  }

  public Double getPaymentAmount()
  {
    return paymentAmount;
  }

  public void setPaymentAmount(Double paymentAmount)
  {
    this.paymentAmount = paymentAmount;
  }

  public String getCreatedBy()
  {
    return createdBy;
  }

  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getCard_number()
  {
    return card_number;
  }

  public void setCard_number(String card_number)
  {
    this.card_number = card_number;
  }

}
