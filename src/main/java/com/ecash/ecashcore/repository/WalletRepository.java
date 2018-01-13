package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.QWallet;
import com.ecash.ecashcore.model.cms.Wallet;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface WalletRepository extends BaseQuerydslRepository<Wallet, String, QWallet>, QueryByExampleExecutor<Wallet> {
}
