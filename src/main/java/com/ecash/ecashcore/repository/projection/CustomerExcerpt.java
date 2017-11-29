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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Projection(name = "custom", types = Customer.class)
public interface CustomerExcerpt {
  String getId();

  CustomerType getCustomerType();

  String getScmsMemberCode();

  String getFirstName();

  String getLastName();

  String getPhone1();

  String getPhone2();

  String getEmail();

  Organization getOrganization();

  Integer getGender();

  Date getDateOfBirth();

  Date getDateBecameCustomer();

  String getStatus();

  String getCountryCode();

  String getOccupation();

  String getTitle();

  String getPosition();

  @JsonProperty(access = Access.WRITE_ONLY)
  List<Account> getAccounts();

  List<Address> getAddresses();

  List<IdentifyDocument> getIdentifyDocuments();

  List<CustomerHistory> getCustomerHistory();
  
  Date getLastLogin();

}
