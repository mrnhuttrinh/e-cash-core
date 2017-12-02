package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.CustomerHistory;
import com.ecash.ecashcore.model.HistoryType;

@Projection(name = "custom", types = CustomerHistory.class)
public interface CustomerHistoryExcerpt {
  String getId();

  String getDetails();

  String getStatus();

  String getCreatedBy();

  CustomerExcerpt getCustomer();

  HistoryType getType();
}
