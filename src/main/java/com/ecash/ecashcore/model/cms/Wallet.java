package com.ecash.ecashcore.model.cms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseUUID;

@Entity
@Table(name = "wallet")
public class Wallet extends BaseUUID {
  
  @Column(name = "type", nullable = false)
  private String type;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "ref_id")
  private String refId;
  
  @Column(name = "provider", nullable = false)
  private String provider;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id", nullable = false)
  private Card card;
  
  private String status;

  public Wallet() {}

  public Wallet(String type, Card card, String status) {
    this.type = type;
    this.card = card;
    this.status = status;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRefId() {
    return refId;
  }

  public void setRefId(String refId) {
    this.refId = refId;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
  
}
