package com.ecash.ecashcore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.querydsl.core.types.Predicate;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;
  
  public Iterable<Customer> findAll(Predicate predicate, Pageable pageable) {
    return customerRepository.findAll(predicate, pageable);
  }
}
