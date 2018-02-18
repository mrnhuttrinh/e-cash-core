package com.ecash.ecashcore.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ecash.ecashcore.vo.CustomerTransactionVO;

public class UserTransactionPOJO {
  private List<CustomerTransactionVO> customerTransactions;
  
  public UserTransactionPOJO() {
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
