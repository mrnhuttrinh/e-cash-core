package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.DiscountByTime;

@Projection(name = "custom", types = DiscountByTime.class)
public interface DiscountByTimeExcerpt {

  public String getId();

  public Date getStartDate();

  public Date getEndDate();

  public Double getDiscountRate();

  public String getStatus();
}
