package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "organization")
public class Organization extends BaseModel {
  @Id
  private String id;

  @Column(name = "short_name")
  private String shortName;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
  @JsonProperty(access = Access.WRITE_ONLY)
  List<Customer> customer;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public List<Customer> getCustomer() {
    return customer;
  }

  public void setCustomer(List<Customer> customer) {
    this.customer = customer;
  }
}
