package com.ecash.ecashcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecash.ecashcore.model.AddressType;
public interface AddressTypeRepository extends JpaRepository<AddressType, String> {

}
