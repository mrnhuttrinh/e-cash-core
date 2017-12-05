package com.ecash.ecashcore.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Embeddable
public class TransactionDetailId implements Serializable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -610090990311435938L;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "transaction_id")
  @JsonProperty(access = Access.WRITE_ONLY)
  Transaction transaction;

  public TransactionDetailId() {
    super();
  }

  public TransactionDetailId(Transaction transaction) {
    this.transaction = transaction;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }
}
