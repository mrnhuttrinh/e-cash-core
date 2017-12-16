package com.ecash.ecashcore.model.cms;

import com.ecash.ecashcore.model.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "customer_type")
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
