package com.ecash.ecashcore.model.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ecash.ecashcore.model.BaseUUID;

@Entity(name = "Transaction")
@Table(name = "transaction")
public class Transaction extends BaseUUID
{

  @Column(name = "date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "amount")
  private Double amount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id")
  private Card card;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "related_transaction_id")
  private Transaction relatedTransaction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code")
  private TransactionType transactionType;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "transactionDetailId.transaction")
  TransactionDetail transactionDetail;

  @Column(name = "status")
  private String status;

  public Transaction()
  {
    super();
  }
  public Transaction(Account account, TransactionType transactionType, Date date, Double amount)
  {
    super();
    this.account = account;
    this.transactionType = transactionType;
    this.date = date;
    this.amount = amount;
  }

  public Transaction(Account account, TransactionType transactionType, Date date, Double amount,
      Card card)
  {
    super();
    this.account = account;
    this.transactionType = transactionType;
    this.date = date;
    this.amount = amount;
    this.card = card;
  }

  public void setAccount(Account account)
  {
    this.account = account;
  }

  public Transaction getRelatedTransaction()
  {
    return relatedTransaction;
  }

  public void setRelatedTransaction(Transaction relatedTransaction)
  {
    this.relatedTransaction = relatedTransaction;
  }

  public TransactionType getTransactionType()
  {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType)
  {
    this.transactionType = transactionType;
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

  public Account getAccount()
  {
    return account;
  }

  public Card getCard()
  {
    return card;
  }

  public void setCard(Card card)
  {
    this.card = card;
  }

  public TransactionDetail getTransactionDetail()
  {
    return transactionDetail;
  }

  public void setTransactionDetail(TransactionDetail transactionDetail)
  {
    this.transactionDetail = transactionDetail;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public static Transaction activeOf(Account account, TransactionType transactionType, Date date, Double amount,
                                     Card card) {
    Transaction transaction = new Transaction(account, transactionType, date, amount, card);
    transaction.setStatus("ACTIVE");
    return transaction;
  }
  
  public static Transaction activeOf(Account account, TransactionType transactionType, Date date, Double amount) {
    Transaction transaction = new Transaction(account, transactionType, date, amount);
    transaction.setStatus("ACTIVE");
    return transaction;
  }
}
