package com.ecash.ecashcore.model.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ecash.ecashcore.model.BaseUUID;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "card_statement")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CardStatement extends BaseUUID
{

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_number")
  private Card card;

  @Column(name = "company")
  private String company;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_code", nullable = false)
  private CurrencyCode currencyCode;

  @Column(name = "due_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dueDate;

  @Column(name = "current_amount")
  private Double currentAmount;

  @Column(name = "payment_amount")
  private Double paymentAmount;

  @Column(name = "created_by")
  private String createdBy;

  public CardStatement()
  {
    super();
  }

  public CardStatement(Card card, String company, CurrencyCode currencyCode, Date dueDate,
      Double currentAmount, Double paymentAmount, String createdBy)
  {
    super();
    this.card = card;
    this.company = company;
    this.currencyCode = currencyCode;
    this.dueDate = dueDate;
    this.currentAmount = currentAmount;
    this.paymentAmount = paymentAmount;
    this.createdBy = createdBy;
  }

  public Card getCard()
  {
    return card;
  }

  public void setCard(Card card)
  {
    this.card = card;
  }

  public String getCompany()
  {
    return company;
  }

  public void setCompany(String company)
  {
    this.company = company;
  }

  public CurrencyCode getCurrencyCode()
  {
    return currencyCode;
  }

  public void setCurrencyCode(CurrencyCode currencyCode)
  {
    this.currencyCode = currencyCode;
  }

  public Date getDueDate()
  {
    return dueDate;
  }

  public void setDueDate(Date dueDate)
  {
    this.dueDate = dueDate;
  }

  public Double getCurrentAmount()
  {
    return currentAmount;
  }

  public void setCurrentAmount(Double currentAmount)
  {
    this.currentAmount = currentAmount;
  }

  public Double getPaymentAmount()
  {
    return paymentAmount;
  }

  public void setPaymentAmount(Double paymentAmount)
  {
    this.paymentAmount = paymentAmount;
  }

  public String getCreatedBy()
  {
    return createdBy;
  }

  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }

}
