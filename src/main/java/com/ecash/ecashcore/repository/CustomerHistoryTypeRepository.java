package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.CustomerHistoryType;
import com.ecash.ecashcore.model.cms.QCustomerHistoryType;

public interface CustomerHistoryTypeRepository
    extends BaseQuerydslRepository<CustomerHistoryType, String, QCustomerHistoryType> {
}
