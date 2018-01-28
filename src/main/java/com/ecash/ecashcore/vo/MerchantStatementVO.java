package com.ecash.ecashcore.vo;

import java.util.Date;

public class MerchantStatementVO
{
  private String id;

  private String company;

  private String currencyCode;

  private Date dueDate;

  private Double openingAmount;

  private Double closingAmount;

  private String createdBy;

  private String merchantId;

  private String cardId;

  private Long totalTransaction;

  private String status;

  private boolean isSettlement;

  private MerchantStatementDetailVO merchantStatementDetailVO;

  public MerchantStatementVO()
  {
    super();
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
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

  public Double getOpeningAmount()
  {
    return openingAmount;
  }

  public void setOpeningAmount(Double openingAmount)
  {
    this.openingAmount = openingAmount;
  }

  public Double getClosingAmount()
  {
    return closingAmount;
  }

  public void setClosingAmount(Double closingAmount)
  {
    this.closingAmount = closingAmount;
  }

  public String getCreatedBy()
  {
    return createdBy;
  }

  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }

  public MerchantStatementDetailVO getMerchantStatementDetailVO()
  {
    return merchantStatementDetailVO;
  }

  public void setMerchantStatementDetailVO(MerchantStatementDetailVO merchantStatementDetailVO)
  {
    this.merchantStatementDetailVO = merchantStatementDetailVO;
  }

  public String getMerchantId()
  {
    return merchantId;
  }

  public void setMerchantId(String merchantId)
  {
    this.merchantId = merchantId;
  }

  public String getCardId()
  {
    return cardId;
  }

  public void setCardId(String cardId)
  {
    this.cardId = cardId;
  }

  public Long getTotalTransaction()
  {
    return totalTransaction;
  }

  public void setTotalTransaction(Long totalTransaction)
  {
    this.totalTransaction = totalTransaction;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public boolean isSettlement()
  {
    return isSettlement;
  }

  public void setSettlement(boolean isSettlement)
  {
    this.isSettlement = isSettlement;
  }

}
