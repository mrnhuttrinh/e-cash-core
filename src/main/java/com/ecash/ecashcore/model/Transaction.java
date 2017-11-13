package com.ecash.ecashcore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  @JsonBackReference
  private Account account;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "related_transaction_id")
  private Transaction relatedTransaction;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code")
  private TransactionType transactionType;

  @Column(name = "date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "amount")
  private Double amount;

  public Transaction() {
    super();
  }

  public Transaction(Account account, TransactionType transactionType, Date date, Double amount) {
    super();
    this.account = account;
    this.transactionType = transactionType;
    this.date = date;
    this.amount = amount;
  }

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

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }
}
