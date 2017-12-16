package com.ecash.ecashcore.repository.projection;

import com.ecash.ecashcore.model.cms.CustomerHistory;
import com.ecash.ecashcore.model.cms.CustomerHistoryType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "custom", types = CustomerHistory.class)
public interface CustomerHistoryExcerpt extends BaseExcerpt {
  String getId();

  String getDetails();

  String getStatus();

  String getCreatedBy();

  CustomerExcerpt getCustomer();

  CustomerHistoryType getType();
}
