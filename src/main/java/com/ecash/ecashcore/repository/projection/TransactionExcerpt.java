package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.Transaction;
import com.ecash.ecashcore.model.TransactionDetail;
import com.ecash.ecashcore.model.TransactionType;

@Projection(name = "custom", types = Transaction.class)
public interface TransactionExcerpt {

  public String getId();

  public Date getDate();

  public Double getAmount();

  public Account getAccount();

  public Card getCard();

  public Transaction getRelatedTransaction();

  public TransactionType getTransactionType();
  
  TransactionDetail getTransactionDetail();
}
