package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.ecash.ecashcore.model.Address;
@RestResource(exported = false)
public interface AddressRepository extends JpaRepository<Address, String> {

}
