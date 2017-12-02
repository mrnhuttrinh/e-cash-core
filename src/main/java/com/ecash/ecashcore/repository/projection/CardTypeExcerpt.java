package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.CardType;

@Projection(name = "custom", types = CardType.class)
public interface CardTypeExcerpt {
}
