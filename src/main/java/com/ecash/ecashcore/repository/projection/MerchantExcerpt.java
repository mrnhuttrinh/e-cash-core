package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Merchant;

@Projection(name = "custom", types = Merchant.class)
public interface MerchantExcerpt extends BaseExcerpt {

  public String getId();

  public String getName();

  public String getEmail();

  public String getStatus();

  public Address getAddress();
  
  public String getPhone();

  public void setPhone(String phone);

}
