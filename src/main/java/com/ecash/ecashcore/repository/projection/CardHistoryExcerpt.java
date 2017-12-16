package com.ecash.ecashcore.repository.projection;

import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.CardHistory;
import com.ecash.ecashcore.model.cms.CardHistoryType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "custom", types = CardHistory.class)
public interface CardHistoryExcerpt extends BaseExcerpt {

  public String getId();

  public String getDetails();

  public String getStatus();

  public Card getCard();

  public CardHistoryType getType();

  public String getCreatedBy();

}
