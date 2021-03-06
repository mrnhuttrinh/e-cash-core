package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.CustomerType;

@Projection(name = "custom", types = CustomerType.class)
public interface CustomerTypeExcerpt extends TypeExcerpt {
}
