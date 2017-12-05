package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;
import com.ecash.ecashcore.model.Merchant;
import com.ecash.ecashcore.model.MerchantHistory;
import com.ecash.ecashcore.model.User;

@Projection(name = "custom", types = MerchantHistory.class)
public interface MerchantHistoryExcerpt extends BaseExcerpt {
  String getId();

  String getDetails();

  User getCreatedBy();

  Merchant getMerchant();

  HistoryTypeExcerpt getType();

}
