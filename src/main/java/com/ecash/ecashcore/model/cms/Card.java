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
@Table(name = "card")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "cardNumber")
public class Card extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "status")
  private String status;

  @Column(name = "card_code", unique = true)
  private String cardCode;

  @Column(name = "effective_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date effectiveDate;

  @Column(name = "expiry_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date expiryDate;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code")
  private CardType cardType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = false)
  private Account account;
  
  @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<CardHistory> cardHistories;
  
  @JsonProperty(access = Access.WRITE_ONLY)
  @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
  private List<Wallet> wallets;

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public CardType getCardType() {
    return cardType;
  }

  public void setCardType(CardType cardType) {
    this.cardType = cardType;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCardCode() {
    return cardCode;
  }

  public void setCardCode(String cardCode) {
    this.cardCode = cardCode;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public List<CardHistory> getCardHistories() {
    return cardHistories;
  }

  public void setCardHistories(List<CardHistory> cardHistories) {
    this.cardHistories = cardHistories;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public List<Wallet> getWallets() {
    return wallets;
  }

  public void setWallets(List<Wallet> wallets) {
    this.wallets = wallets;
  }
  
}
