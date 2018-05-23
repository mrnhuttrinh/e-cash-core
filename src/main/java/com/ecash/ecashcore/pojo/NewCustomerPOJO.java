package com.ecash.ecashcore.pojo;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.Organization;

public class NewCustomerPOJO  {
  private Customer customer;
  private Address address;
  private IdentifyDocument indetifyCard;
  private Organization organization;

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

  public Organization getOrganization() {
    return organization;
  }

  public void setOrganization(Organization organization) {
    this.organization = organization;
  }
}
