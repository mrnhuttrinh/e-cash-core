package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.wallet.EWalletType;
import com.ecash.ecashcore.model.wallet.QEWalletType;

public interface EwalletTypeRepository extends BaseQuerydslRepository<EWalletType, String, QEWalletType> {

  EWalletType findByTypeCode(String typeCode);
}
