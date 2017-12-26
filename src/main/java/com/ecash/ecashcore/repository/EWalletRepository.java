package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.wallet.EWallet;
import com.ecash.ecashcore.model.wallet.QEWallet;

public interface EWalletRepository extends BaseQuerydslRepository<EWallet, String, QEWallet> {

}
