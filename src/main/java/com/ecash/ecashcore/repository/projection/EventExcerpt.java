package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import com.ecash.ecashcore.model.cms.AccountEvent;
import com.ecash.ecashcore.model.cms.AccountEventType;
import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Account;

@Projection(name = "custom", types = AccountEvent.class)
public interface EventExcerpt extends BaseExcerpt {

  public String getId();

  public Date getDate();

  public Account getAccount();

  public AccountEventType getEventType();
}
