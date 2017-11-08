package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.Merchant;;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

}
