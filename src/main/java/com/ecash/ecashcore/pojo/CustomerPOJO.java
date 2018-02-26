package com.ecash.ecashcore.pojo;

import java.util.List;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Customer;

public class CustomerPOJO {
  private Customer customer;
  private List<Account> accounts;
  
  public CustomerPOJO(Customer customer, List<Account> accounts) {
    super();
    this.customer = customer;
    this.accounts = accounts;
  }

  public CustomerPOJO() {
    super();
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }
}
