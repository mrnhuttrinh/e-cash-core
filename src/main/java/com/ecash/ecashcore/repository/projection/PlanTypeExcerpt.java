package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.PlanType;

@Projection(name = "custom", types = PlanType.class)
public interface PlanTypeExcerpt extends TypeExcerpt {
}
