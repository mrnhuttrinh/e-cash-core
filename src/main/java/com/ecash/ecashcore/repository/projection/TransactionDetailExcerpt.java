package com.ecash.ecashcore.repository.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.Transaction;
import com.ecash.ecashcore.model.cms.TransactionDetail;

@Projection(name = "custom", types = TransactionDetail.class)
public interface TransactionDetailExcerpt extends BaseExcerpt {

  @Value("#{target.transactionDetailId.transaction}")
  public Transaction getTransaction();

  public String getDetail();

  public String getStatus();

  public Merchant getMerchant();
}
