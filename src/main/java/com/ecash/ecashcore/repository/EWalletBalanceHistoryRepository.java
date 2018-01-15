package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.wallet.EBalanceHistory;
import com.ecash.ecashcore.model.wallet.QEBalanceHistory;

public interface EWalletBalanceHistoryRepository extends BaseQuerydslRepository<EBalanceHistory, String, QEBalanceHistory> {

}
