package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.CurrencyCode;
import com.ecash.ecashcore.model.cms.Customer;

@Projection(name = "custom", types = Account.class)
public interface AccountExcerpt extends BaseExcerpt {
  public String getId();

  public String getAccountName();

  public Date getDateOpened();

  public Date getDateClosed();

  public Double getCurrentBalance();

  public String getStatus();

  public AccountTypeExcerpt getAccountType();

  public Customer getCustomer();

  public PlanExcerpt getPlan();

  public CurrencyCode getCurrencyCode();

}
