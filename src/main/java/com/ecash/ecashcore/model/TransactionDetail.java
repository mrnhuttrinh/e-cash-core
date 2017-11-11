package com.ecash.ecashcore.model;

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

  @Column(name = "product_type")
  private String productType;

  @Column(name = "product_details")
  private String productDetail;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id")
  private Merchant merchant;

  @Column(name = "status")
  private String status;

  public TransactionDetail() {
    super();
  }

  public TransactionDetail(Transaction transaction, String productDetail, Merchant merchant) {
    super();
    this.transactionDetailId = new TransactionDetailId(transaction);
    this.productDetail = productDetail;
    this.merchant = merchant;
  }

  public TransactionDetailId getTransactionDetailId() {
    return transactionDetailId;
  }

  public void setTransactionDetailId(TransactionDetailId transactionDetailId) {
    this.transactionDetailId = transactionDetailId;
  }

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public String getProductDetail() {
    return productDetail;
  }

  public void setProductDetail(String productDetail) {
    this.productDetail = productDetail;
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
