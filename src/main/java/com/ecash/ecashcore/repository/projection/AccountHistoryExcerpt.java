package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.AccountHistory;
import com.ecash.ecashcore.model.HistoryType;

@Projection(name = "custom", types = AccountHistory.class)
public interface AccountHistoryExcerpt {
  public String getId();

  public String getDetails();

  public String getStatus();

  public Account getAccount();

  public HistoryType getType();
}
