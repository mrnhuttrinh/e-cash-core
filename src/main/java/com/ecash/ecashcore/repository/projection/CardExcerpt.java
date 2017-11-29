package com.ecash.ecashcore.repository.projection;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.AccountType;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.CardType;
import com.ecash.ecashcore.model.CurrencyCode;

@Projection(name = "custom", types = Card.class)
public interface CardExcerpt {
  String getCardNumber();

  @Value("#{target.cardType}")
  public CardType getCardType();

  public String getStatus();
  public String getCardCode();
  public Date getEffectiveDate();

  public Date getExpiryDate() ;
}
