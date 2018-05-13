package com.ecash.ecashcore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.constants.StringConstant;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.DataNotFoundException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.ecash.ecashcore.util.StringUtils;
import com.querydsl.core.types.Predicate;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;
  
  public Iterable<Customer> findAll(Predicate predicate, Pageable pageable) {
    return customerRepository.findAll(predicate, pageable);
  }

  public void lockCustomers(List<Customer> vos) {
    for (Customer vo : vos) {
      if (StringUtils.isNullOrEmpty(vo.getId())) {
        throw new ValidationException("Customer ids must not be null or emtpy.");
      }

      Customer customer = customerRepository.findOne(vo.getId());
      if (customer == null) {
        throw new DataNotFoundException("Customer could not be found. Id: " + vo.getId());
      }

      if (!customer.getStatus().equals(StatusEnum.DEACTIVE.toString())) {
        customer.setStatus(StatusEnum.DEACTIVE.toString());
        customerRepository.save(customer);
      }
    }
  }

  public void unlockCustomers(List<Customer> vos) {
    for (Customer vo : vos) {
      if (StringUtils.isNullOrEmpty(vo.getId())) {
        throw new ValidationException("Customer ids must not be null or emtpy.");
      }

      Customer customer = customerRepository.findOne(vo.getId());
      if (customer == null) {
        throw new DataNotFoundException("Customer could not be found. Id: " + vo.getId());
      }

      if (!customer.getStatus().equals(StatusEnum.ACTIVE.toString())) {
        customer.setStatus(StatusEnum.ACTIVE.toString());
        customerRepository.save(customer);
      }
    }
  }
}
