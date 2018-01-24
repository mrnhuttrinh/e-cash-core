package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_detail")
public class TransactionDetail extends BaseModel {

  @EmbeddedId
  TransactionDetailId transactionDetailId;

  @Column(name = "detail")
  private String detail;

  @Column(name = "status")
  private String status;

  // TODO: remove nullable = true
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id", nullable = true)
  private Merchant merchant;

  public TransactionDetail() {
    super();
  }

  public TransactionDetail(Transaction transaction, String detail) {
    super();
    this.transactionDetailId = new TransactionDetailId(transaction);
    this.detail = detail;
  }

  public TransactionDetail(Transaction transaction, String detail, Merchant merchant) {
    super();
    this.transactionDetailId = new TransactionDetailId(transaction);
    this.detail = detail;
    this.merchant = merchant;
  }

  public TransactionDetailId getTransactionDetailId() {
    return transactionDetailId;
  }

  public void setTransactionDetailId(TransactionDetailId transactionDetailId) {
    this.transactionDetailId = transactionDetailId;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
