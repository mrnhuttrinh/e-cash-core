package com.ecash.ecashcore.repository.projection;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.AccountHistory;
import com.ecash.ecashcore.model.cms.AccountHistoryType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "custom", types = AccountHistory.class)
public interface AccountHistoryExcerpt extends BaseExcerpt {
  public String getId();

  public String getDetails();

  public String getStatus();

  public Account getAccount();

  public AccountHistoryType getType();

  public String getCreatedBy();

}
