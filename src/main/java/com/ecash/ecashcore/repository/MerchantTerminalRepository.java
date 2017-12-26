package com.ecash.ecashcore.repository;

import java.util.List;

import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.MerchantTerminal;
import com.ecash.ecashcore.model.cms.QMerchantTerminal;

public interface MerchantTerminalRepository
    extends BaseQuerydslRepository<MerchantTerminal, String, QMerchantTerminal> {

  public MerchantTerminal findByPubKey(String pubKey);

  public List<MerchantTerminal> findByMerchant(Merchant merchant);
}
