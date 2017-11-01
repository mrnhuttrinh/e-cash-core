package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "transaction")
public class Transaction extends BaseModel {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id", nullable = true)
  private Account accountId;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "related_transaction_id", nullable = true)
  private Transaction relatedTransactionId;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "type_code", nullable = true)
  private TransactionType typeCode;

  private Date date;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "currency_code", nullable = true)
  private CurrencyCode currencyCode;

  private Double amount;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Account getAccountId() {
    return accountId;
  }

  public void setAccountId(Account accountId) {
    this.accountId = accountId;
  }

  public Transaction getRelatedTransactionId() {
    return relatedTransactionId;
  }

  public void setRelatedTransactionId(Transaction relatedTransactionId) {
    this.relatedTransactionId = relatedTransactionId;
  }

  public TransactionType getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(TransactionType typeCode) {
    this.typeCode = typeCode;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public CurrencyCode getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(CurrencyCode currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
