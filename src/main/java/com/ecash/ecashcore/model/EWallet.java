package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "e_wallet")
public class EWallet extends BaseModel {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "type_code", nullable = false)
  private EWalletType typeCode;

  @Column(name = "date_opened")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOpened;

  @Column(name = "currency_code")
  private String currencyCode;

  @Column(name = "date_closed")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateClosed;

  @Column(name = "current_balance")
  private Double currentBalance;

  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public EWalletType getTypeCode() {
    return typeCode;
  }

  public void setTypeCode(EWalletType typeCode) {
    this.typeCode = typeCode;
  }

  public Date getDateOpened() {
    return dateOpened;
  }

  public void setDateOpened(Date dateOpened) {
    this.dateOpened = dateOpened;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Date getDateClosed() {
    return dateClosed;
  }

  public void setDateClosed(Date dateClosed) {
    this.dateClosed = dateClosed;
  }

  public double getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(double currentBalance) {
    this.currentBalance = currentBalance;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
