package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.AccountType;

@Projection(name = "custom", types = AccountType.class)
public interface AccountTypeExcerpt extends BaseExcerpt {
  String getTypeCode();
  
  String getDescription();
}
