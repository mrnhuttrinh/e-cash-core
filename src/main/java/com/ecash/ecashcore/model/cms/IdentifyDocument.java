package com.ecash.ecashcore.model.cms;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "identify_document")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class IdentifyDocument extends BaseModel {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  private String description;

  private String number;

  @Column(name = "date_of_issue")
  private Date dateOfIssue;

  @Column(name = "date_of_expiry")
  private Date dateOfExpiry;

  @Column(name = "place_of_issue")
  private String placeOfIssue;

  private String status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "type_code", nullable = true)
  private IdentifyDocumentType identifyDocumentType;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "customer_identify_document", joinColumns = @JoinColumn(name = "identify_document_id"), inverseJoinColumns = @JoinColumn(name = "customer_id"))
  private List<Customer> customers;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public IdentifyDocumentType getIdentifyDocumentType() {
    return identifyDocumentType;
  }

  public void setIdentifyDocumentType(IdentifyDocumentType identifyDocumentType) {
    this.identifyDocumentType = identifyDocumentType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Date getDateOfIssue() {
    return dateOfIssue;
  }

  public void setDateOfIssue(Date dateOfIssue) {
    this.dateOfIssue = dateOfIssue;
  }

  public Date getDateOfExpiry() {
    return dateOfExpiry;
  }

  public void setDateOfExpiry(Date dateOfExpiry) {
    this.dateOfExpiry = dateOfExpiry;
  }

  public String getPlaceOfIssue() {
    return placeOfIssue;
  }

  public void setPlaceOfIssue(String placeOfIssue) {
    this.placeOfIssue = placeOfIssue;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }
}
