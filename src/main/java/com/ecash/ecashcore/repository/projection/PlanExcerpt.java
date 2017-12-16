package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Plan;

@Projection(name = "custom", types = Plan.class)
public interface PlanExcerpt extends BaseExcerpt {

  public String getId();

  public Integer getLimit();

  public String getStatus();

  public PlanTypeExcerpt getPlanType();
}
