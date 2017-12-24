package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.wallet.EWallet;

public interface EwalletRepository extends JpaRepository<EWallet, String> {

}
