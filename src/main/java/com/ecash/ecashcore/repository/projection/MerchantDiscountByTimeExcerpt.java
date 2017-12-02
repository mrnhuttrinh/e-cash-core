package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.DiscountByTime;
import com.ecash.ecashcore.model.Merchant;
import com.ecash.ecashcore.model.MerchantDiscountByTime;

@Projection(name = "custom", types = MerchantDiscountByTime.class)
public interface MerchantDiscountByTimeExcerpt {
  
  public String getId();

  public Merchant getMerchant();

  public DiscountByTime getDiscountByTime();
}
