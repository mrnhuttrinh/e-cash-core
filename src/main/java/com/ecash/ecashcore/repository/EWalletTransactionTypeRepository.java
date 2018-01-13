package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.wallet.EWalletTransactionType;
import com.ecash.ecashcore.model.wallet.QEWalletTransactionType;

public interface EWalletTransactionTypeRepository extends BaseQuerydslRepository<EWalletTransactionType, String, QEWalletTransactionType> {

}
