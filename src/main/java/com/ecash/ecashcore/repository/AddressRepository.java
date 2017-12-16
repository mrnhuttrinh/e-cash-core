package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.Address;
public interface AddressRepository extends JpaRepository<Address, String> {

}
