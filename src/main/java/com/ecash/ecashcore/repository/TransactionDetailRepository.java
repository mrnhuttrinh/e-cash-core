package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.TransactionDetail;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, String> {

}
