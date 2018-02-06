package com.ecash.ecashcore.model.cms;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecash.ecashcore.model.BaseUUID;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "customer_identify_document")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CustomerIdentifyDocument extends BaseUUID {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = true)
  private Customer customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "identify_document_id", nullable = true)
  private IdentifyDocument identifyDocument;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public IdentifyDocument getIdentifyDocument() {
    return identifyDocument;
  }

  public void setIdentifyDocument(IdentifyDocument identifyDocument) {
    this.identifyDocument = identifyDocument;
  }

}
