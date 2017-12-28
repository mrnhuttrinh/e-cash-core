package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.MerchantHistory;
import com.ecash.ecashcore.model.cms.QMerchantHistory;

public interface MerchantHistoryRepository extends BaseQuerydslRepository<MerchantHistory, String, QMerchantHistory> {
}
