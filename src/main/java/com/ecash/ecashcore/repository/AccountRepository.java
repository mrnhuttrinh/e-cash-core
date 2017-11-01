package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
