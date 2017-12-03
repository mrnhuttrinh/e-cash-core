package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.DiscountByTier;
import com.ecash.ecashcore.model.Merchant;
import com.ecash.ecashcore.model.MerchantDiscountByTier;

@Projection(name = "custom", types = MerchantDiscountByTier.class)
public interface MerchantDiscountByTierExcerpt extends BaseExcerpt {

  public String getId();

  public Merchant getMerchant();

  public DiscountByTier getDiscountByTier();
}
