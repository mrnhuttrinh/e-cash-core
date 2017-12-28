package com.ecash.ecashcore.repository.projection;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.cms.Permission;
import com.ecash.ecashcore.model.cms.Role;
import com.ecash.ecashcore.model.cms.User;

@Projection(name = "custom", types = Role.class)
public interface RoleExcerpt extends BaseExcerpt {

  public String getId();

  public String getName();

//  public List<User> getUsers();

  public List<Permission> getPermissions();
}
