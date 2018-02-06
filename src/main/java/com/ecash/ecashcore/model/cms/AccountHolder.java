package com.ecash.ecashcore.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseUUID;

@Entity
@Table(name = "account_holder")
public class AccountHolder extends BaseUUID {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;

  @Column(name = "holder_id", nullable = false)
  private String holderId;

  @Column(name = "holder_type", nullable = false)
  private String holderType;

  public AccountHolder() {
    super();
  }

  public AccountHolder(String id, Account account, String holderId, String holderType) {
    super();
    this.id = id;
    this.account = account;
    this.holderId = holderId;
    this.holderType = holderType;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public String getHolderId() {
    return holderId;
  }

  public void setHolderId(String holderId) {
    this.holderId = holderId;
  }

  public String getHolderType() {
    return holderType;
  }

  public void setHolderType(String holderType) {
    this.holderType = holderType;
  }
}
