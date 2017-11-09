package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.TransactionType;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, String> {

}
