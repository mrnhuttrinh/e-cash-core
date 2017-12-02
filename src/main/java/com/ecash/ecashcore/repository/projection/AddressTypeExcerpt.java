package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.AddressType;

@Projection(name = "custom", types = AddressType.class)
public interface AddressTypeExcerpt {
}
