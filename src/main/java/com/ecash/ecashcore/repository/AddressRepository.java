package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.cms.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

  List<Address> findByLine1AndLine2AndCountry(String line1, String line2, String country);
}
