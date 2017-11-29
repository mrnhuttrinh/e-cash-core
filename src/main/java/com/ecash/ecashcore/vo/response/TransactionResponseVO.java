package com.ecash.ecashcore.vo.response;

import java.util.Date;

public class TransactionResponseVO {
  private String id;
  private String account;
  private String relatedTransaction;
  private Date date;

  public TransactionResponseVO(String id, String account, Date date) {
    super();
    this.id = id;
    this.account = account;
    this.date = date;
  }

  public TransactionResponseVO(String id, String account, Date date, String relatedTransaction) {
    super();
    this.id = id;
    this.account = account;
    this.relatedTransaction = relatedTransaction;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getRelatedTransaction() {
    return relatedTransaction;
  }

  public void setRelatedTransaction(String relatedTransaction) {
    this.relatedTransaction = relatedTransaction;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
