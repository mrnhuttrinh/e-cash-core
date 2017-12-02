package com.ecash.ecashcore.repository.projection;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Address;
import com.ecash.ecashcore.model.AddressType;
import com.ecash.ecashcore.model.Customer;

@Projection(name = "custom", types = Address.class)
public interface AddressExcerpt {

  public String getId();

  public String getLine1();

  public String getLine2();

  public String getCity();
  public Integer getZipCode();

  public String getStateProvince();

  public String getCountry();

  public String getStatus();

  public AddressType getAddressType();

  public List<Customer> getCustomers();
}
