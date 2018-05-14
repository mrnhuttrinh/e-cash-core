package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.IdentifyDocument;

public class NewCustomerVO  {
  private Customer customer;
  private Address address;
  private IdentifyDocument indetifyCard;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public IdentifyDocument getIndetifyCard() {
    return indetifyCard;
  }

  public void setIndetifyCard(IdentifyDocument indetifyCard) {
    this.indetifyCard = indetifyCard;
  }
}
