package com.ecash.ecashcore.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.HistoryType;
import com.ecash.ecashcore.model.User;
import com.ecash.ecashcore.model.UserHistory;

@Projection(name = "custom", types = UserHistory.class)
public interface UserHistoryExcerpt {
  String getId();

  User getCreatedBy();
  
  HistoryType getType();
  
  User getUser();
  
  Date getCreatedAt();
  
  Date getUpdatedAt();
}
