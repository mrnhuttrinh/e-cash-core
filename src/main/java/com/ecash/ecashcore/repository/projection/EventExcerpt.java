package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.Event;
import com.ecash.ecashcore.model.EventType;

@Projection(name = "custom", types = Event.class)
public interface EventExcerpt extends BaseExcerpt {

  public String getId();

  public Date getDate();

  public Account getAccount();

  public EventType getEventType();
}
