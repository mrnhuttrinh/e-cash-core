package com.ecash.ecashcore.model;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "card_history")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CardHistory extends BaseModel {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(name = "history_details", nullable = true)
  private String details;

  private String status;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_number", nullable = false)
  private Card card;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "history_type", nullable = false)
  private HistoryType type;
  
  @Column(name = "created_by", nullable = true)
  private String createdBy;

  public CardHistory() {
    super();
  }

  public CardHistory(Card card, HistoryType type, String details) {
    super();
    this.card = card;
    this.type = type;
    this.details = details;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public HistoryType getType() {
    return type;
  }

  public void setType(HistoryType type) {
    this.type = type;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

}
