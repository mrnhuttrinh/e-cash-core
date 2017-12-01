package com.ecash.ecashcore.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "card")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class, 
    property = "cardNumber")
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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

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

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
