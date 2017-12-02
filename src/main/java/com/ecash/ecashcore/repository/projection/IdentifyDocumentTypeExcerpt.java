package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.IdentifyDocumentType;

@Projection(name = "custom", types = IdentifyDocumentType.class)
public interface IdentifyDocumentTypeExcerpt {
}
