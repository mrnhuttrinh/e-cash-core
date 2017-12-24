package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.wallet.EWalletType;

public interface EwalletTypeRepository extends JpaRepository<EWalletType, String> {

}
