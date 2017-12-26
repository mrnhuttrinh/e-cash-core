package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.BalanceHistory;
import com.ecash.ecashcore.model.cms.QBalanceHistory;

public interface BalanceHistoryRepository extends BaseQuerydslRepository<BalanceHistory, String, QBalanceHistory> {

}
