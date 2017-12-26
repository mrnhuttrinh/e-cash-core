package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.AccountHistoryType;
import com.ecash.ecashcore.model.cms.QAccountHistoryType;

public interface AccountHistoryTypeRepository
    extends BaseQuerydslRepository<AccountHistoryType, String, QAccountHistoryType> {
}
