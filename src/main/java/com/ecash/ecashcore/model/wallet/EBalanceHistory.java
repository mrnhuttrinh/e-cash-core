package com.ecash.ecashcore.model.wallet;

import com.ecash.ecashcore.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "e_balance_history")
public class EBalanceHistory extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
