package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Merchant;
import com.ecash.ecashcore.model.MerchantTerminal;

@Projection(name = "custom", types = MerchantTerminal.class)
public interface MerchantTerminalExcerpt {

  public String getId();

  public String getPubKey();

  public Date getPubKeyExpireDate();

  public Merchant getMerchant();
}
