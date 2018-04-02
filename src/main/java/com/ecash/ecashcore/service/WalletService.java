package com.ecash.ecashcore.service;

import com.ecash.ecashcore.enums.CurrencyCodeEnum;
import com.ecash.ecashcore.enums.EWalletTypeEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Wallet;
import com.ecash.ecashcore.model.wallet.EWallet;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.CurrencyCodeRepository;
import com.ecash.ecashcore.repository.EWalletRepository;
import com.ecash.ecashcore.repository.EWalletTypeRepository;
import com.ecash.ecashcore.repository.WalletRepository;
import com.ecash.ecashcore.util.ObjectUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class WalletService {
  private static final String SWT = "SWT";
  private static final String DEFAULT = "DEFAULT";

  @Autowired
  WalletRepository walletRepository;
  
  @Autowired
  EWalletRepository eWalletRepository;
  
  @Autowired
  EWalletTypeRepository eWalletTypeRepository;
  
  @Autowired
  CardRepository cardRepository;
  
  @Autowired
  CurrencyCodeRepository currencyCodeRepository;

  public Iterable<Wallet> findAll(Predicate predicate, Pageable pageable) {
    Page<Wallet> wallets = walletRepository.findAll(predicate, pageable);
    for(Wallet wallet : wallets) {
      wallet.setCard(ObjectUtils.clone(wallet.getCard(), Card.class));
    }
    
    return wallets;
  }
  
  public Wallet createWallet(Wallet data) {
    Wallet wallet = new Wallet();
    wallet.setProvider(SWT);
    wallet.setType(DEFAULT);
    wallet.setCard(cardRepository.findByCardNumber(data.getCard().getCardNumber()));
    wallet.setName(data.getName());
    wallet.setStatus(StatusEnum.ACTIVE.getName());
    
    EWallet eWallet = new EWallet();
    eWallet.setCurrentBalance(Double.valueOf(0));
    eWallet.setEWalletType(eWalletTypeRepository.findByTypeCode(EWalletTypeEnum.DEFAULT.toString()));
    eWallet.setDateOpened(new Date());
    eWallet.setStatus(StatusEnum.ACTIVE.getName());
    eWallet.setCurrencyCode(CurrencyCodeEnum.VND.toString());
    eWalletRepository.save(eWallet);
    
    wallet.setRefId(eWallet.getId());
    walletRepository.save(wallet);
    
    return wallet;
  }

  public Wallet disconnectWallet(String id) {
    Wallet wallet = walletRepository.getOne(id);
    
    if (wallet == null) {
      return null;
    }
    
    // DEACTIVE
    wallet.setCard(null);
    wallet.setStatus(StatusEnum.DEACTIVE.getName());
    wallet.setRefId(null);
    
    return walletRepository.save(wallet);
  }

}
