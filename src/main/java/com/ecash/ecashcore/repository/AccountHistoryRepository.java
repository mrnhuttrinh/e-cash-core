package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.AccountHistory;
import com.ecash.ecashcore.model.cms.QAccountHistory;

public interface AccountHistoryRepository extends BaseQuerydslRepository<AccountHistory, String, QAccountHistory> {
}
