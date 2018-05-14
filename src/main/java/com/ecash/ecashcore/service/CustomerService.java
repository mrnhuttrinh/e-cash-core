package com.ecash.ecashcore.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecash.ecashcore.constants.StringConstant;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.DataNotFoundException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.CustomerAddress;
import com.ecash.ecashcore.model.cms.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.repository.AddressRepository;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.ecash.ecashcore.repository.CustomerIdentifyDocumentsRepository;
import com.ecash.ecashcore.repository.CustomerAddressRepository;
import com.ecash.ecashcore.repository.IdentifyDocumentRepository;
import com.ecash.ecashcore.util.StringUtils;
import com.querydsl.core.types.Predicate;
import com.ecash.ecashcore.vo.request.NewCustomerVO;

@Service
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  CustomerAddressRepository customerAddressRepository;

  @Autowired
  CustomerIdentifyDocumentsRepository customerIdentifyDocumentsRepository;

  @Autowired
  IdentifyDocumentRepository identifyDocumentRepository;
  
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
  public Customer addNewCustomer(NewCustomerVO newCustomerVO) {
    // save customer
    Customer customer = customerRepository.saveAndFlush(newCustomerVO.getCustomer());

    // save address
    Address address = addressRepository.saveAndFlush(newCustomerVO.getAddress());

    CustomerAddress customerAddress = new CustomerAddress();
    customerAddress.setAddress(address);
    customerAddress.setCustomer(customer);
    customerAddress.setStatus(StatusEnum.ACTIVE.toString());
    customerAddressRepository.save(customerAddress);

    // save
    IdentifyDocument newIndetifyCard = identifyDocumentRepository.saveAndFlush(newCustomerVO.getIndetifyCard());

    CustomerIdentifyDocument customerIdentifyDocument = new CustomerIdentifyDocument();
    customerIdentifyDocument.setIdentifyDocument(newIndetifyCard);
    customerIdentifyDocument.setCustomer(customer);
    customerIdentifyDocumentsRepository.save(customerIdentifyDocument);

    // create history

    return customer;
  }
}
