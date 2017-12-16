package com.ecash.ecashcore.repository.projection;

import com.ecash.ecashcore.model.cms.AccountEventType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "custom", types = AccountEventType.class)
public interface EventTypeExcerpt extends TypeExcerpt {
}
