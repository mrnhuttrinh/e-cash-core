package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.CustomerHistory;
import com.ecash.ecashcore.model.cms.QCustomerHistory;

public interface CustomerHistoryRepository extends BaseQuerydslRepository<CustomerHistory, String, QCustomerHistory> {
}
