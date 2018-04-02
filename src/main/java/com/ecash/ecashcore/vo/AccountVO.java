package com.ecash.ecashcore.vo;

import java.util.Date;

public class AccountVO {
  private String id;
  private String accountName;
  private Date dateOpened;
  private Date dateClosed;
  private Double currentBalance;
  private String status;

  public AccountVO() {
    super();
  }

  public AccountVO(String id, String accountName, Date dateOpened, Date dateClosed, Double currentBalance,
      String status) {
    super();
    this.id = id;
    this.accountName = accountName;
    this.dateOpened = dateOpened;
    this.dateClosed = dateClosed;
    this.currentBalance = currentBalance;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public Date getDateOpened() {
    return dateOpened;
  }

  public void setDateOpened(Date dateOpened) {
    this.dateOpened = dateOpened;
  }

  public Date getDateClosed() {
    return dateClosed;
  }

  public void setDateClosed(Date dateClosed) {
    this.dateClosed = dateClosed;
  }

  public Double getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(Double currentBalance) {
    this.currentBalance = currentBalance;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
