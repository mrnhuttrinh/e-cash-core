package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.QUser;
import com.ecash.ecashcore.model.cms.User;

public interface UserRepository extends BaseQuerydslRepository<User, String, QUser> {
  User findByEmail(String email);

  User findByUsername(String username);
}
