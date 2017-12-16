package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Transaction;
import com.ecash.ecashcore.model.cms.TransactionDetail;
import com.ecash.ecashcore.model.cms.TransactionType;

@Projection(name = "custom", types = Transaction.class)
public interface TransactionExcerpt extends BaseExcerpt {

  public String getId();

  public Date getDate();

  public Double getAmount();

  public Account getAccount();

  public Card getCard();

  public Transaction getRelatedTransaction();

  public TransactionType getTransactionType();
  
  TransactionDetail getTransactionDetail();
}
