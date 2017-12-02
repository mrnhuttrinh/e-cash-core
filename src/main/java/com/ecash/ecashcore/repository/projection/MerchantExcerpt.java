package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Address;
import com.ecash.ecashcore.model.Merchant;

@Projection(name = "custom", types = Merchant.class)
public interface MerchantExcerpt {

  public String getId();

  public String getName();

  public String getEmail();

  public String getStatus();

  public Address getAddress();

  public void setPhone(String phone);

}
