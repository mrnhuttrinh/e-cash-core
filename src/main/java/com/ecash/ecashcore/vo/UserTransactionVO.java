package com.ecash.ecashcore.vo;

import java.util.ArrayList;
import java.util.List;

public class UserTransactionVO {
  private List<CustomerTransactionVO> customerTransactions;
  
  public UserTransactionVO() {
    super();
    this.customerTransactions = new ArrayList<CustomerTransactionVO>();
  }

  public List<CustomerTransactionVO> getCustomerTransactions() {
    return customerTransactions;
  }

  public void setCustomerTransactions(List<CustomerTransactionVO> customerTransactions) {
    this.customerTransactions = customerTransactions;
  }
}
