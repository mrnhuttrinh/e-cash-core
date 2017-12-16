package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;
import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.MerchantHistory;
import com.ecash.ecashcore.model.cms.User;

@Projection(name = "custom", types = MerchantHistory.class)
public interface MerchantHistoryExcerpt extends BaseExcerpt {
  String getId();

  String getDetails();

  User getCreatedBy();

  Merchant getMerchant();

  HistoryTypeExcerpt getType();

}
