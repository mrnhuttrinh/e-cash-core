package com.ecash.ecashcore.pojo;

import java.util.List;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.IdentifyDocument;

public class UpdateCustomerPOJO  {
  private Customer customer;
  private List<Address> addresses;
  private List<IdentifyDocument> indetifyCards;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public List<IdentifyDocument> getIndetifyCards() {
    return indetifyCards;
  }

  public void setIndetifyCards(List<IdentifyDocument> indetifyCards) {
    this.indetifyCards = indetifyCards;
  }
}
