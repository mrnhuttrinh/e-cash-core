package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.BalanceHistory;

@Projection(name = "custom", types = BalanceHistory.class)
public interface BalanceHistoryExcerpt extends BaseExcerpt {

  public String getId();

  public Date getDate();

  public Double getBalance();

  public Account getAccount();
}
