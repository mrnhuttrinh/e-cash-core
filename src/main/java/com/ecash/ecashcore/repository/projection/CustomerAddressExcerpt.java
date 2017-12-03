package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Address;
import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.CustomerAddress;

@Projection(name = "custom", types = CustomerAddress.class)
public interface CustomerAddressExcerpt extends BaseExcerpt {

  public String getId();

  public String getStatus();

  public Customer getCustomer();

  public Address getAddress();
}
