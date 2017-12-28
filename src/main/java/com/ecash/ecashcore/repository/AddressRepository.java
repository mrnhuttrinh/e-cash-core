package com.ecash.ecashcore.repository;

import java.util.List;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.QAddress;

public interface AddressRepository extends BaseQuerydslRepository<Address, String, QAddress> {

  List<Address> findByLine1AndLine2AndCountry(String line1, String line2, String country);
}
