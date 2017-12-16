package com.ecash.ecashcore.repository.projection;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.Organization;

@Projection(name = "custom", types = Organization.class)
public interface OrganizationExcerpt extends BaseExcerpt {
  String getId();
  
  String getShortName();
  
  List<Customer> getCustomer();
}
