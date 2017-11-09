package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.AccountType;

public interface AccountTypeRepository extends JpaRepository<AccountType, String> {

}
