package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.wallet.EWalletTransaction;
import com.ecash.ecashcore.model.wallet.QEWalletTransaction;

public interface EWalletTransactionRepository extends BaseQuerydslRepository<EWalletTransaction, String, QEWalletTransaction> {

}
