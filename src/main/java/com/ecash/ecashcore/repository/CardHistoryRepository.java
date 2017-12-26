package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.CardHistory;
import com.ecash.ecashcore.model.cms.QCardHistory;

public interface CardHistoryRepository extends BaseQuerydslRepository<CardHistory, String, QCardHistory> {
}
