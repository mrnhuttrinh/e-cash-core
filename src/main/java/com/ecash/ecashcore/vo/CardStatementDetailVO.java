package com.ecash.ecashcore.vo;

import java.util.Date;

public class CardStatementDetailVO
{

  private String description;

  private String transactionTypeCode;

  private Double transactionAmount;

  private String createdBy;

  private Date transactionDate;

  public CardStatementDetailVO()
  {
    super();
  }

  public CardStatementDetailVO(String description, String transactionTypeCode,
      Double transactionAmount, String createdBy, Date transactionDate)
  {
    super();
    this.description = description;
    this.transactionTypeCode = transactionTypeCode;
    this.transactionAmount = transactionAmount;
    this.createdBy = createdBy;
    this.transactionDate = transactionDate;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public String getTransactionTypeCode()
  {
    return transactionTypeCode;
  }

  public void setTransactionTypeCode(String transactionTypeCode)
  {
    this.transactionTypeCode = transactionTypeCode;
  }

  public Double getTransactionAmount()
  {
    return transactionAmount;
  }

  public void setTransactionAmount(Double transactionAmount)
  {
    this.transactionAmount = transactionAmount;
  }

  public String getCreatedBy()
  {
    return createdBy;
  }

  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }

  public Date getTransactionDate()
  {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate)
  {
    this.transactionDate = transactionDate;
  }

}
