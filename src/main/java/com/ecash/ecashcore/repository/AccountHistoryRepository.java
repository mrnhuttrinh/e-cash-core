package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.AccountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryRepository extends JpaRepository<AccountHistory, String> {
}
