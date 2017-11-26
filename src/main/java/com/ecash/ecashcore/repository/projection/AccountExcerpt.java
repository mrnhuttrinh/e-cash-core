package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.AccountType;
import com.ecash.ecashcore.model.CurrencyCode;

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
  
  public CurrencyCode getCurrencyCode();
  
  @Value("#{target.customer.firstName}")
  public String getCustomerFirstName();
  
  @Value("#{target.customer.lastName}")
  public String getCustomerLastName();
}
