package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.AccountCard;

public interface AccountCardRepository extends JpaRepository<AccountCard, String> {

}
