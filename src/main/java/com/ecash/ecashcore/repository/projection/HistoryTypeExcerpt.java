package com.ecash.ecashcore.repository.projection;

import com.ecash.ecashcore.model.AbstractHistoryType;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "custom", types = AbstractHistoryType.class)
public interface HistoryTypeExcerpt extends BaseExcerpt {
  public String getType();
  
  public String getName();
  
  public String getDescription();
  
  public String getStatus();

}
