package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.BalanceHistory;

public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, String> {

}
