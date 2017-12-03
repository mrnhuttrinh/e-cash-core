package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.HistoryType;

@Projection(name = "custom", types = HistoryType.class)
public interface HistoryTypeExcerpt extends BaseExcerpt {
}
