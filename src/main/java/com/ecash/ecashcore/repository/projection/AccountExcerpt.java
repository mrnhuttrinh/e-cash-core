package com.ecash.ecashcore.repository.projection;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.AccountHistory;
import com.ecash.ecashcore.model.AccountType;
import com.ecash.ecashcore.model.CurrencyCode;
import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.Plan;

@Projection(name = "custom", types = Account.class)
public interface AccountExcerpt extends BaseExcerpt {
  public String getId();

  public String getAccountName();

  public Date getDateOpened();

  public Date getDateClosed();

  public Double getCurrentBalance();

  public String getStatus();

  public AccountType getAccountType();

  public Customer getCustomer();

  public Plan getPlan();

  public CurrencyCode getCurrencyCode();

  public List<AccountHistory> getAccountHistories();
}