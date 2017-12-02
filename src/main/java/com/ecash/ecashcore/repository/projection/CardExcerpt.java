package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.CardType;
import com.ecash.ecashcore.model.Customer;

@Projection(name = "custom", types = Card.class)
public interface CardExcerpt {

  public String getCardNumber();

  public String getStatus();

  public String getCardCode();

  public Date getEffectiveDate();

  public Date getExpiryDate();

  public CardType getCardType();

  public Customer getCustomer();
}
