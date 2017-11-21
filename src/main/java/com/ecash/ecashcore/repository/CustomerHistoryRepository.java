package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.CustomerHistory;

public interface CustomerHistoryRepository extends JpaRepository<CustomerHistory, String> {
}
