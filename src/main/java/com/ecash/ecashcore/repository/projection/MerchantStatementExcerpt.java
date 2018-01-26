package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.CurrencyCode;
import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.MerchantStatement;

@Projection(name = "custom", types = MerchantStatement.class)
public interface MerchantStatementExcerpt extends BaseExcerpt {
  public String getId();
  public Merchant getMerchant();
  public CurrencyCode getCurrencyCode();
  public Date getDueDate();
  public Double getOpeningAmount();
  public Double getClosingAmount();
  public Long getTotalTransaction();
  public String getStatus();
  public String getCreatedBy();
}
