package com.ecash.ecashcore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "customer_type")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "typeCode")
public class CustomerType extends Type {

  @OneToMany(mappedBy = "customerType")
  @JsonProperty(access = Access.WRITE_ONLY)
  private List<Customer> customer;

  public List<Customer> getCustomer() {
    return customer;
  }

  public void setCustomer(List<Customer> customer) {
    this.customer = customer;
  }
}