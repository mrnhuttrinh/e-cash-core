package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.QTransactionType;
import com.ecash.ecashcore.model.cms.TransactionType;

public interface TransactionTypeRepository extends BaseQuerydslRepository<TransactionType, String, QTransactionType> {

}
