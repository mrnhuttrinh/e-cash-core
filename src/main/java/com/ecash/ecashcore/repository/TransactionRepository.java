package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
