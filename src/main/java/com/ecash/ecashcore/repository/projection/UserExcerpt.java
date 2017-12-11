package com.ecash.ecashcore.repository.projection;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.ecash.ecashcore.model.Role;
import com.ecash.ecashcore.model.User;

@Projection(name = "custom", types = User.class)
public interface UserExcerpt extends BaseExcerpt {

  String getId();

  String getUsername();

  String getFirstName();

  String getLastName();

  String getEmail();

//  String getPassword();

  boolean isEnabled();

  boolean isUsing2FA();
  
  Date getLastLogin();
  
  List<Role> getRoles();
  
  String getSetting();

}
