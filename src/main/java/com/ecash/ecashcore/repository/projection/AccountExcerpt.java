package com.ecash.ecashcore.repository.projection;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.AccountType;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.CurrencyCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Projection(name = "custom", types = Account.class)
public interface AccountExcerpt {
  String getId();

  String getAccountName();

  Date getDateOpened();

  Date getDateClosed();

  Double getCurrentBalance();

  String getStatus();

  @Value("#{target.accountType}")
  AccountType getAccountType();
  
  CurrencyCode getCurrencyCode();
  
  @Value("#{target.customer.firstName}")
  String getCustomerFirstName();
  
  @Value("#{target.customer.lastName}")
  String getCustomerLastName();
  
  @Value("#{target.cards}")
  List<Card> getCards();
}
