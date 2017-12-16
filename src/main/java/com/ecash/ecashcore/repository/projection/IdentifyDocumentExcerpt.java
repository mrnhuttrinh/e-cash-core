package com.ecash.ecashcore.repository.projection;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.IdentifyDocumentType;

@Projection(name = "custom", types = IdentifyDocument.class)
public interface IdentifyDocumentExcerpt extends BaseExcerpt {
  public String getId();

  public String getDescription();

  public String getNumber();

  public Date getDateOfIssue();

  public Date getDateOfExpiry();

  public String getPlaceOfIssue();

  public String getStatus();

  public IdentifyDocumentType getIdentifyDocumentType();

  public List<Customer> getCustomers();
}
