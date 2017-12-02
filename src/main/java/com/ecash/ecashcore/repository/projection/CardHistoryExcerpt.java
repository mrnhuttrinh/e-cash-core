package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.CardHistory;
import com.ecash.ecashcore.model.HistoryType;

@Projection(name = "custom", types = CardHistory.class)
public interface CardHistoryExcerpt {

  public String getId();

  public String getDetails();

  public String getStatus();

  public Card getCard();

  public HistoryType getType();
}
