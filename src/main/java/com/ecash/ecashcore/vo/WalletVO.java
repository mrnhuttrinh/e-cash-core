package com.ecash.ecashcore.vo;

import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Wallet;

public class WalletVO {

private String id;
  
  private String type;
  
  private String name;
  
  private String refId;
  
  private String provider;
  
  // TODO: use VO, don't use entity
  private Card card;

  public WalletVO(Wallet wallet) {
    id = wallet.getId();
    type = wallet.getType();
    name = wallet.getName();
    refId = wallet.getRefId();
    provider = wallet.getProvider();
    card = wallet.getCard();
  }
  public WalletVO() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }
  
}
