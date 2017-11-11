package com.ecash.ecashcore.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
  private List<AccountHistory> accountHistories;

  @Column(name = "current_balance")
  private Double currentBalance;

  @Column(name = "status")
  private String status;

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
  private List<AccountCard> accountCards;

  public Account() {
    super();
  }

  public Account(AccountType accountType, Customer customer, CurrencyCode currencyCode) {
    super();
    this.accountType = accountType;
    this.customer = customer;
    this.currencyCode = currencyCode;
  }

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

  public List<AccountCard> getAccountCards() {
    return accountCards;
  }

  public void setAccountCards(List<AccountCard> accountCards) {
    this.accountCards = accountCards;
  }

  public List<Card> getCard() {
    List<Card> cards = new LinkedList<>();
    accountCards.stream().forEach(c -> cards.add(c.getCard()));
    return cards;
  }

  public List<AccountHistory> getAccountHistories() {
    return accountHistories;
  }

  public void setAccountHistories(List<AccountHistory> accountHistories) {
    this.accountHistories = accountHistories;
  }
}
