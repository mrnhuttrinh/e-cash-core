package com.ecash.ecashcore.repository.projection;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.AddressType;
import com.ecash.ecashcore.model.cms.Customer;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@Projection(name = "custom", types = Address.class)
public interface AddressExcerpt extends BaseExcerpt {

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
