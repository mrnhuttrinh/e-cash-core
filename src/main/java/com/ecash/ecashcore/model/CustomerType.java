package com.ecash.ecashcore.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "customer_type")
public class CustomerType extends Type {
  
  @OneToMany(mappedBy = "customerType")
  @JsonBackReference
  private List<Customer> customer;

  public List<Customer> getCustomer() {
    return customer;
  }

  public void setCustomer(List<Customer> customer) {
    this.customer = customer;
  }
}
