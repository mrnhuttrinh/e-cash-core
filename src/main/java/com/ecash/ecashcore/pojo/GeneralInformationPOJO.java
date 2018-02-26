package com.ecash.ecashcore.pojo;

import java.util.List;

import com.ecash.ecashcore.model.cms.User;

public class GeneralInformationPOJO {
  private User user;
  private List<CustomerPOJO> customers;
  
  public GeneralInformationPOJO(User user, List<CustomerPOJO> customers) {
    super();
    this.user = user;
    this.customers = customers;
  }

  public GeneralInformationPOJO() {
    super();
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CustomerPOJO> getCustomers() {
    return customers;
  }

  public void setCustomers(List<CustomerPOJO> customers) {
    this.customers = customers;
  }
}
