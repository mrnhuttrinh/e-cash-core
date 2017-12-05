package com.ecash.ecashcore.repository.projection;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value; 
import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.CardHistory;
import com.ecash.ecashcore.model.CardType;
import com.ecash.ecashcore.model.Customer;

@Projection(name = "custom", types = Card.class)
public interface CardExcerpt extends BaseExcerpt {

  public String getCardNumber();

  public String getStatus();

  public String getCardCode();

  public Date getEffectiveDate();

  public Date getExpiryDate();

  public CardType getCardType();

  public Customer getCustomer();
  
  @Value("#{target.customer.accounts}") 
  List<Account> getAccounts();
  
  public List<CardHistory> getCardHistories();
  
}
