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
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = true)
  private Account account;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "related_transaction_id", nullable = true)
  private Transaction relatedTransaction;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code", nullable = true)
  private TransactionType transactionType;

  private Date date;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_code", nullable = true)
  private CurrencyCode currencyCode;

  private Double amount;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Account getaccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Transaction getRelatedTransaction() {
    return relatedTransaction;
  }

  public void setRelatedTransaction(Transaction relatedTransaction) {
    this.relatedTransaction = relatedTransaction;
  }

  public TransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType) {
    this.transactionType = transactionType;
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
