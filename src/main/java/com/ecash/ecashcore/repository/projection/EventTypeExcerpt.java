package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.EventType;

@Projection(name = "custom", types = EventType.class)
public interface EventTypeExcerpt extends BaseExcerpt {
}
