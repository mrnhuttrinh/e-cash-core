package com.ecash.ecashcore.repository.projection;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.Address;
import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.CustomerHistory;
import com.ecash.ecashcore.model.CustomerType;
import com.ecash.ecashcore.model.IdentifyDocument;
import com.ecash.ecashcore.model.Organization;

@Projection(name = "custom", types = Customer.class)
public interface CustomerExcerpt {
  public String getId();

  public CustomerType getCustomerType();

  public String getScmsMemberCode();

  public String getFirstName();

  public String getLastName();

  public String getPhone1();

  public String getPhone2();

  public String getEmail();

  public Organization getOrganization();

  public Integer getGender();

  public Date getDateOfBirth();


  public Date getDateBecameCustomer();
 
  public String getStatus();


  public String getCountryCode();


  public String getOccupation();


  public String getTitle();


  public String getPosition();


  public List<Account> getAccounts();


  public List<Address> getAddresses();


  public List<IdentifyDocument> getIdentifyDocuments();


  public List<CustomerHistory> getCustomerHistory();
  
}
