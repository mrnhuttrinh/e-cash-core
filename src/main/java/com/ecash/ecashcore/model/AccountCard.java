package com.ecash.ecashcore.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "account_card")
public class AccountCard extends BaseModel {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id", nullable = true)
  private Account accountId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", nullable = true)
  private Card cardId;

  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Account getAccountId() {
    return accountId;
  }

  public void setAccountId(Account accountId) {
    this.accountId = accountId;
  }

  public Card getCardId() {
    return cardId;
  }

  public void setCardId(Card cardId) {
    this.cardId = cardId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
