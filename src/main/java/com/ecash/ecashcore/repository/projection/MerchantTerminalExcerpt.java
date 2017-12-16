package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.MerchantTerminal;

@Projection(name = "custom", types = MerchantTerminal.class)
public interface MerchantTerminalExcerpt extends BaseExcerpt {

  public String getId();

  public String getPubKey();

  public Date getPubKeyExpireDate();

  public Merchant getMerchant();
  
  public String getName();
  
  public String getStatus();
}
