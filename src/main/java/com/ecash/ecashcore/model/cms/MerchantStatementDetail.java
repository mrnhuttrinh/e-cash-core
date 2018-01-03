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
@Table(name = "merchant_statement_detail")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MerchantStatementDetail extends BaseModel
{

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_statement_id")
  private MerchantStatement merchantStatement;

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

  public MerchantStatementDetail()
  {
    super();
  }

  public String getId()
  {
    return id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public MerchantStatement getMerchantStatement()
  {
    return merchantStatement;
  }

  public void setMerchantStatement(MerchantStatement merchantStatement)
  {
    this.merchantStatement = merchantStatement;
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

  public Date getTransactionDate()
  {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate)
  {
    this.transactionDate = transactionDate;
  }

}
