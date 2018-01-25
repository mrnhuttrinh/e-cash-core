package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.MerchantStatementDetail;
import com.ecash.ecashcore.model.cms.TransactionType;

@Projection(name = "custom", types = MerchantStatementDetail.class)
public interface MerchantStatementDetailExcerpt extends BaseExcerpt {
  public String getId();

  public String getDescription();
  public TransactionType getTransactionType();

  public Double getTransactionAmount();

  public String getCreatedBy();

  public Date getTransactionDate();
}
