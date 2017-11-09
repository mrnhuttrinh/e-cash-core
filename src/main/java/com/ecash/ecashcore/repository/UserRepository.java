package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.User;

public interface UserRepository extends JpaRepository<User, String> {
  User findByEmail(String email);

}
