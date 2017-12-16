package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.AbstractHistory;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "card_history")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CardHistory extends AbstractHistory<CardHistoryType> {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_number", nullable = false)
  private Card card;

  public CardHistory(Card card, CardHistoryType type, String details) {
    super(type);
    this.card = card;
    this.setDetails(details);
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }
}
