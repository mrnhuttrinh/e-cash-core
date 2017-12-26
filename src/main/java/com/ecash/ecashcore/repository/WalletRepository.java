package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.QWallet;
import com.ecash.ecashcore.model.cms.Wallet;

public interface WalletRepository extends BaseQuerydslRepository<Wallet, String, QWallet> {
}
