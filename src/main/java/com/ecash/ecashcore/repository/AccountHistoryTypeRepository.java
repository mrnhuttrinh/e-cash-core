package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.AccountHistoryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountHistoryTypeRepository extends JpaRepository<AccountHistoryType, String> {
}
