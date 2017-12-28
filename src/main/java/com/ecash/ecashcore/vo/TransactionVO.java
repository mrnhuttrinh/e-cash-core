package com.ecash.ecashcore.vo;

import java.util.Date;

public class TransactionVO
{
  private String id;

  private Date date;

  private Double amount;

  private String accountId;

  private String cardNumber;

  private String relatedTransactionId;

  private String transactionType;

  private String status;

  private Date createdAt;

  private Date updatedAt;

  private String transactionDetailDetail;

  private String transactionDetailStatus;

  private String transactionDetailMerchantId;

  private Double accountCurrentBalance;

  private String accountCurrencyCode;

  private String accountCustomerOrganizationShortName;

  public TransactionVO()
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

  public Date getDate()
  {
    return date;
  }

  public void setDate(Date date)
  {
    this.date = date;
  }

  public Double getAmount()
  {
    return amount;
  }

  public void setAmount(Double amount)
  {
    this.amount = amount;
  }

  public String getAccountId()
  {
    return accountId;
  }

  public void setAccountId(String accountId)
  {
    this.accountId = accountId;
  }

  public String getCardNumber()
  {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber)
  {
    this.cardNumber = cardNumber;
  }

  public String getRelatedTransactionId()
  {
    return relatedTransactionId;
  }

  public void setRelatedTransactionId(String relatedTransactionId)
  {
    this.relatedTransactionId = relatedTransactionId;
  }

  public String getTransactionType()
  {
    return transactionType;
  }

  public void setTransactionType(String transactionType)
  {
    this.transactionType = transactionType;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt)
  {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt()
  {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt)
  {
    this.updatedAt = updatedAt;
  }

  public String getTransactionDetailDetail()
  {
    return transactionDetailDetail;
  }

  public void setTransactionDetailDetail(String transactionDetailDetail)
  {
    this.transactionDetailDetail = transactionDetailDetail;
  }

  public String getTransactionDetailStatus()
  {
    return transactionDetailStatus;
  }

  public void setTransactionDetailStatus(String transactionDetailStatus)
  {
    this.transactionDetailStatus = transactionDetailStatus;
  }

  public String getTransactionDetailMerchantId()
  {
    return transactionDetailMerchantId;
  }

  public void setTransactionDetailMerchantId(String transactionDetailMerchantId)
  {
    this.transactionDetailMerchantId = transactionDetailMerchantId;
  }

  public Double getAccountCurrentBalance()
  {
    return accountCurrentBalance;
  }

  public void setAccountCurrentBalance(Double accountCurrentBalance)
  {
    this.accountCurrentBalance = accountCurrentBalance;
  }

  public String getAccountCurrencyCode()
  {
    return accountCurrencyCode;
  }

  public void setAccountCurrencyCode(String accountCurrencyCode)
  {
    this.accountCurrencyCode = accountCurrencyCode;
  }

  public String getAccountCustomerOrganizationShortName()
  {
    return accountCustomerOrganizationShortName;
  }

  public void setAccountCustomerOrganizationShortName(String accountCustomerOrganizationShortName)
  {
    this.accountCustomerOrganizationShortName = accountCustomerOrganizationShortName;
  }

}
