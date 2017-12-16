package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.CustomerAddress;

@Projection(name = "custom", types = CustomerAddress.class)
public interface CustomerAddressExcerpt extends BaseExcerpt {

  public String getId();

  public String getStatus();

  public Customer getCustomer();

  public Address getAddress();
}
