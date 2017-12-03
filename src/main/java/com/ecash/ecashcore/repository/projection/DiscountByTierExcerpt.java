package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.DiscountByTier;

@Projection(name = "custom", types = DiscountByTier.class)
public interface DiscountByTierExcerpt extends BaseExcerpt {

  public String getId();

  public Double getDiscountRate();

  public String getStatus();
}
