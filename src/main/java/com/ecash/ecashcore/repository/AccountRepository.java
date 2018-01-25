package com.ecash.ecashcore.repository;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.QAccount;

@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
public interface AccountRepository extends BaseQuerydslRepository<Account, String, QAccount> {

  List<Account> findByCustomer(Customer customer);
}
