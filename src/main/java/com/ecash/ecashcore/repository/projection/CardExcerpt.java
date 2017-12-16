package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.CardType;

@Projection(name = "custom", types = Card.class)
public interface CardExcerpt extends BaseExcerpt {

  public String getCardNumber();

  public String getStatus();

  public String getCardCode();

  public Date getEffectiveDate();

  public Date getExpiryDate();

  public CardType getCardType();

  
}
