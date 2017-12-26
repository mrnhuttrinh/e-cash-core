package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.QUserHistory;
import com.ecash.ecashcore.model.cms.UserHistory;

public interface UserHistoryRepository extends BaseQuerydslRepository<UserHistory, String, QUserHistory> {
}
