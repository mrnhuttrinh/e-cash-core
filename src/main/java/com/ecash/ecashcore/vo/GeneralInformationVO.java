package com.ecash.ecashcore.vo;

import java.util.List;

import com.ecash.ecashcore.model.cms.User;

public class GeneralInformationVO {
  private User user;
  private List<CustomerVO> customers;
  
  public GeneralInformationVO(User user, List<CustomerVO> customers) {
    super();
    this.user = user;
    this.customers = customers;
  }

  public GeneralInformationVO() {
    super();
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<CustomerVO> getCustomers() {
    return customers;
  }

  public void setCustomers(List<CustomerVO> customers) {
    this.customers = customers;
  }
}
