package com.ecash.ecashcore.model.wallet;

import com.ecash.ecashcore.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "e_wallet_transaction")
public class EWalletTransaction extends BaseModel {

  public static String SUCCESS = "SUCCESS";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id", nullable = false)
  private EWallet walletId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "related_transaction_id")
  private EWalletTransaction relatedTransaction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code")
  private EWalletTransactionType transactionType;

  private Date date;

  private Double amount;

  private String status;

  public EWalletTransaction()
  {
    super();
  }

  public EWalletTransaction(EWallet walletId, EWalletTransactionType transactionType, Date date, Double amount, String status) {
    super();
    this.walletId = walletId;
    this.transactionType = transactionType;
    this.date = date;
    this.amount = amount;
    this.status = status;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EWallet getWalletId() {
    return walletId;
  }

  public void setWalletId(EWallet walletId) {
    this.walletId = walletId;
  }

  public EWalletTransaction getRelatedTransaction() {
    return relatedTransaction;
  }

  public void setRelatedTransaction(EWalletTransaction relatedTransaction) {
    this.relatedTransaction = relatedTransaction;
  }

  public EWalletTransactionType getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(EWalletTransactionType transactionType) {
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
