package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

  Role findByName(String name);

}
