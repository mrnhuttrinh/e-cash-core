package com.ecash.ecashcore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "account")
public class Account extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code", nullable = true)
  private AccountType accountType;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = true)
  private Customer customer;

  @Column(name = "account_name")
  private String accountName;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_code", nullable = true)
  private CurrencyCode currencyCode;

  @Column(name = "date_opened")
  private Date dateOpened;

  @Column(name = "current_balance")
  private Double currentBalance;

  @Column(name = "status")
  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountType accountType) {
    this.accountType = accountType;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public CurrencyCode getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(CurrencyCode currencyCode) {
    this.currencyCode = currencyCode;
  }

  public Date getDateOpened() {
    return dateOpened;
  }

  public void setDateOpened(Date dateOpened) {
    this.dateOpened = dateOpened;
  }

  public Double getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(Double currentBalance) {
    this.currentBalance = currentBalance;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
