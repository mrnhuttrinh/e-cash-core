package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.wallet.EWalletType;

public interface EWalletTypeRepository extends JpaRepository<EWalletType, String> {
  EWalletType findByTypeCode(String typeCode);
}
