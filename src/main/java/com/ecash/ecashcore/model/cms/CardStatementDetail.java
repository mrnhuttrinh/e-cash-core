package com.ecash.ecashcore.model.cms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.ecash.ecashcore.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "card_statement_detail")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CardStatementDetail extends BaseModel
{

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_statement_id")
  private CardStatement cardStatement;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code", nullable = false)
  private TransactionType transactionType;

  @Column(name = "transaction_amount")
  private Double transactionAmount;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "transaction_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date transactionDate;

  public CardStatementDetail()
  {
    super();
  }

  public CardStatementDetail(CardStatement cardStatement, String description,
      TransactionType transactionType, Double transactionAmount, String createdBy,
      Date transactionDate)
  {
    super();
    this.cardStatement = cardStatement;
    this.description = description;
    this.transactionType = transactionType;
    this.transactionAmount = transactionAmount;
    this.createdBy = createdBy;
    this.transactionDate = transactionDate;
  }

  public Date getTransactionDate()
  {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate)
  {
    this.transactionDate = transactionDate;
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public CardStatement getCardStatement()
  {
    return cardStatement;
  }

  public void setCardStatement(CardStatement cardStatement)
  {
    this.cardStatement = cardStatement;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public TransactionType getTransactionType()
  {
    return transactionType;
  }

  public void setTransactionType(TransactionType transactionType)
  {
    this.transactionType = transactionType;
  }

  public Double getTransactionAmount()
  {
    return transactionAmount;
  }

  public void setTransactionAmount(Double transactionAmount)
  {
    this.transactionAmount = transactionAmount;
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
