package com.ecash.ecashcore.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "type_code", nullable = true)
  private AccountType accountType;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id", nullable = true)
  private Customer customer;

  @Column(name = "account_name")
  private String accountName;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "currency_code", nullable = true)
  private CurrencyCode currencyCode;

  @Column(name = "date_opened")
  private Date dateOpened;

  @OneToMany(mappedBy="account", fetch = FetchType.LAZY)
  private Set<AccountHistory> accountHistories;

  @Column(name = "current_balance")
  private Double currentBalance;

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

  public Set<AccountHistory> getAccountHistories() {
    return accountHistories;
  }

  public void setAccountHistories(Set<AccountHistory> accountHistories) {
    this.accountHistories = accountHistories;
  }
}
