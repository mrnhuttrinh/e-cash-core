package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.QCustomer;

public interface CustomerRepository extends BaseQuerydslRepository<Customer, String, QCustomer> {

  Customer findByScmsMemberCode(String scmsMemberCode);
}
