package com.ecash.ecashcore.model.wallet;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ecash.ecashcore.model.BaseUUID;

@Entity
@Table(name = "e_balance_history")
public class EBalanceHistory extends BaseUUID {

  @Column(name = "date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  @Column(name = "balance")
  private Double balance;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "wallet_id")
  private EWallet wallet;

  public EBalanceHistory(Date date, EWallet wallet, Double balance) {
    super();
    this.date = date;
    this.wallet = wallet;
    this.balance = balance;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public EWallet getWallet() {
    return wallet;
  }

  public void setWallet(EWallet wallet) {
    this.wallet = wallet;
  }
}
