package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.CustomerAddress;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, String> {

}
