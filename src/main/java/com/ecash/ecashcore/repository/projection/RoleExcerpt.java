package com.ecash.ecashcore.repository.projection;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Permission;
import com.ecash.ecashcore.model.Role;
import com.ecash.ecashcore.model.User;

@Projection(name = "custom", types = Role.class)
public interface RoleExcerpt {

  public String getId();

  public String getName();

  public List<User> getUsers();

  public List<Permission> getPermissions();
}
