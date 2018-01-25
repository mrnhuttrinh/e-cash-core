package com.ecash.ecashcore.model.cms;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.ecash.ecashcore.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "merchant_statement")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MerchantStatement extends BaseModel
{

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id")
  private String id;
  
  @OneToMany(mappedBy = "merchantStatement", fetch = FetchType.LAZY)
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<MerchantStatementDetail> merchantStatementDetails;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "merchant_id")
  private Merchant merchant;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "currency_code", nullable = false)
  private CurrencyCode currencyCode;

  @Column(name = "due_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date dueDate;

  @Column(name = "opening_amount")
  private Double openingAmount;

  @Column(name = "closing_amount")
  private Double closingAmount;

  @Column(name = "created_by")
  private String createdBy;

  public MerchantStatement()
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

  public List<MerchantStatementDetail> getMerchantStatementDetails() {
    return merchantStatementDetails;
  }

  public void setMerchantStatementDetails(
      List<MerchantStatementDetail> merchantStatementDetails) {
    this.merchantStatementDetails = merchantStatementDetails;
  }

  public Merchant getMerchant()
  {
    return merchant;
  }

  public void setMerchant(Merchant merchant)
  {
    this.merchant = merchant;
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

  public Double getOpeningAmount()
  {
    return openingAmount;
  }

  public void setOpeningAmount(Double openingAmount)
  {
    this.openingAmount = openingAmount;
  }

  public Double getClosingAmount()
  {
    return closingAmount;
  }

  public void setClosingAmount(Double closingAmount)
  {
    this.closingAmount = closingAmount;
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
