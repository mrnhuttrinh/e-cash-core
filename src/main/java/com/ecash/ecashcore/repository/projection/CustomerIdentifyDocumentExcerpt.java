package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.IdentifyDocument;

@Projection(name = "custom", types = CustomerIdentifyDocument.class)
public interface CustomerIdentifyDocumentExcerpt {

  public String getId();

  public Customer getCustomer();

  public IdentifyDocument getIdentifyDocument();
}
