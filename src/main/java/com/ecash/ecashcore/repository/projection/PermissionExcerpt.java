package com.ecash.ecashcore.repository.projection;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Permission;
import com.ecash.ecashcore.model.Role;

@Projection(name = "custom", types = Permission.class)
public interface PermissionExcerpt extends BaseExcerpt {
  
  String getId();
  
  String getName();
  
  List<Role> getRoles();
}
