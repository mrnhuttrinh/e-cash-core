package com.ecash.ecashcore.repository.projection;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.Address;
import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.CustomerHistory;
import com.ecash.ecashcore.model.CustomerType;
import com.ecash.ecashcore.model.Organization;

@Projection(name = "custom", types = Customer.class)
public interface CustomerExcerpt extends BaseExcerpt {
  String getId();

  String getScmsMemberCode();

  String getFirstName();

  String getLastName();

  String getPhone1();

  String getPhone2();

  String getEmail();

  Integer getGender();

  Date getDateOfBirth();

  Date getDateBecameCustomer();

  String getStatus();

  String getCountryCode();

  String getOccupation();

  String getTitle();

  String getPosition();

  CustomerType getCustomerType();

  Organization getOrganization();

  List<Account> getAccounts();

  List<Address> getAddresses();

  List<IdentifyDocumentExcerpt> getIdentifyDocuments();

  List<CustomerHistory> getCustomerHistory();

}
