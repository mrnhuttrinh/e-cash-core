package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Plan;
import com.ecash.ecashcore.model.PlanType;

@Projection(name = "custom", types = Plan.class)
public interface PlanExcerpt {

  public String getId();

  public Integer getLimit();

  public String getStatus();

  public PlanType getPlanType();
}
