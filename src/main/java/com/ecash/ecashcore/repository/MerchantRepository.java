package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.QMerchant;

public interface MerchantRepository extends BaseQuerydslRepository<Merchant, String, QMerchant> {

}
