package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Account extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;
  
  @Column(name = "account_name")
  private String accountName;

  @Column(name = "date_opened")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateOpened;

  @Column(name = "date_closed")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateClosed;

  @Column(name = "current_balance")
  private Double currentBalance;

  @Column(name = "status")
  private String status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code", nullable = false)
  private AccountType accountType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "plan_id", nullable = false)
  private Plan plan;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_code", nullable = false)
  private CurrencyCode currencyCode;

  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<AccountHistory> accountHistories;
  
  @JsonProperty(access = Access.WRITE_ONLY)
  @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
  private List<Card> cards;

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

  public Date getDateClosed() {
    return dateClosed;
  }

  public void setDateClosed(Date dateClosed) {
    this.dateClosed = dateClosed;
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

  public List<AccountHistory> getAccountHistories() {
    return accountHistories;
  }

  public void setAccountHistories(List<AccountHistory> accountHistories) {
    this.accountHistories = accountHistories;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(Plan plan) {
    this.plan = plan;
  }
  

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }
}
