package com.ecash.ecashcore.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.User;
import com.ecash.ecashcore.model.cms.UserHistory;

@Projection(name = "custom", types = UserHistory.class)
public interface UserHistoryExcerpt extends BaseExcerpt {
  String getId();

  String getDetails();

  User getCreatedBy();

  User getUser();

  HistoryTypeExcerpt getType();

}
