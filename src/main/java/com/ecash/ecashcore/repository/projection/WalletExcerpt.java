package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Wallet;

@Projection(name = "custom", types = Wallet.class)
public interface WalletExcerpt extends BaseExcerpt {

  public String getId();

  public Card getCard();

  public String getType();

  public String getName();

  public String getProvider();
}
