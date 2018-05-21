package com.ecash.ecashcore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.constants.StringConstant;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.DataNotFoundException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.CustomerAddress;
import com.ecash.ecashcore.model.cms.CustomerHistory;
import com.ecash.ecashcore.model.cms.CustomerHistoryType;
import com.ecash.ecashcore.model.cms.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.SCMSSyncDetail;
import com.ecash.ecashcore.pojo.NewCustomerPOJO;
import com.ecash.ecashcore.pojo.UpdateCustomerPOJO;
import com.ecash.ecashcore.repository.AddressRepository;
import com.ecash.ecashcore.repository.CustomerAddressRepository;
import com.ecash.ecashcore.repository.CustomerHistoryRepository;
import com.ecash.ecashcore.repository.CustomerHistoryTypeRepository;
import com.ecash.ecashcore.repository.CustomerIdentifyDocumentsRepository;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.ecash.ecashcore.repository.IdentifyDocumentRepository;
import com.ecash.ecashcore.util.JsonUtils;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.CustomerVO;
import com.ecash.ecashcore.vo.HistoryVO;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class CustomerService {

  @Autowired
  CustomerRepository customerRepository;
  
  @Autowired
  CustomerAddressRepository customerAddressRepository;
  
  @Autowired
  CustomerHistoryTypeRepository customerHistoryTypeRepository;
  
  @Autowired
  CustomerHistoryRepository customerHistoryRepository;

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  CustomerIdentifyDocumentsRepository customerIdentifyDocumentsRepository;

  @Autowired
  IdentifyDocumentRepository identifyDocumentRepository;
  
  public Iterable<Customer> findAll(Predicate predicate, Pageable pageable) {
    return customerRepository.findAll(predicate, pageable);
  }

  public void lockCustomers(List<Customer> vos, Predicate predicate, Pageable pageable) {
    if(vos == null || vos.isEmpty()) {
      vos = (List<Customer>) customerRepository.findAll(predicate);
    }
    
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

  public void unlockCustomers(List<Customer> vos, Predicate predicate, Pageable pageable) {
    if(vos == null || vos.isEmpty()) {
      vos = (List<Customer>) customerRepository.findAll(predicate);
    }
    
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
  
  public Customer addNewCustomer(NewCustomerPOJO newCustomerPOJO, String currentUser) {
    // save customer
    Customer customer = customerRepository.save(newCustomerPOJO.getCustomer());

    // save address
    Address address = addressRepository.save(newCustomerPOJO.getAddress());

    CustomerAddress customerAddress = new CustomerAddress();
    customerAddress.setAddress(address);
    customerAddress.setCustomer(customer);
    customerAddress.setStatus(StatusEnum.ACTIVE.toString());
    customerAddressRepository.save(customerAddress);

    // save identify document
    IdentifyDocument newIndetifyCard = identifyDocumentRepository.save(newCustomerPOJO.getIndetifyCard());

    CustomerIdentifyDocument customerIdentifyDocument = new CustomerIdentifyDocument();
    customerIdentifyDocument.setIdentifyDocument(newIndetifyCard);
    customerIdentifyDocument.setCustomer(customer);
    customerIdentifyDocumentsRepository.save(customerIdentifyDocument);

    // create history
    HistoryVO historyVO = new HistoryVO();
    historyVO.getPrevious().put(StringConstant.PREVIOUS, "");
    historyVO.getNext().put(StringConstant.NEXT, JsonUtils.objectToJsonString(customer));

    // update history
    CustomerHistory customerHistory = new CustomerHistory();
    customerHistory.setCustomer(customer);
    customerHistory.setType(customerHistoryTypeRepository.findOne(CustomerHistoryType.CREATED));
    customerHistory.setCreatedBy(currentUser);
    customerHistory.setDetails(JsonUtils.objectToJsonString(historyVO));
    customerHistoryRepository.save(customerHistory);

    return customer;
  }

  public Customer updateCustomer(UpdateCustomerPOJO updateCustomerPOJO, String currentUser) {

    Customer updateCustomer = updateCustomerPOJO.getCustomer();
    Customer customer = customerRepository.findOne(updateCustomer.getId());
    if(customer == null) {
      throw new DataNotFoundException("Can not find customer id:" + updateCustomer.getId());
    }
    
    // create history
    HistoryVO historyVO = new HistoryVO();
    historyVO.getPrevious().put(StringConstant.PREVIOUS, JsonUtils.objectToJsonString(new CustomerVO(customer)));
    
    // update
    // customer.setScmsMemberCode(syncCustomer.getScmsMemberCode());
    customer.setFirstName(updateCustomer.getFirstName());
    customer.setLastName(updateCustomer.getLastName());
    customer.setGender(updateCustomer.getGender());
    customer.setDateOfBirth(updateCustomer.getDateOfBirth());
    customer.setPhone1(updateCustomer.getPhone1());
    customer.setPhone1(updateCustomer.getPhone2());
    customer.setEmail(updateCustomer.getEmail());
    // customer.setDateBecameCustomer(syncCustomer.getDateBecameCustomer());
    customer.setCountryCode(updateCustomer.getCountryCode());
    customer.setOccupation(updateCustomer.getOccupation());
    customer.setTitle(updateCustomer.getTitle());
    customer.setPosition(updateCustomer.getPosition());
    customerRepository.save(customer);
    
    List<Address> updateAddresses = updateCustomerPOJO.getAddresses();
    for(Address updateAddress : updateAddresses) {
      if(updateAddress.getId() != null) {
        Address address = addressRepository.findOne(updateAddress.getId());
        if(address == null) {
          throw new DataNotFoundException("Address could not be found. Id: " + updateAddress.getId());
        }
        address.setCountry(updateAddress.getCountry());
        address.setLine1(updateAddress.getLine1());
        address.setLine2(updateAddress.getLine2());
        // TODO: update more field if need
        addressRepository.save(address);
      } else {
        Address address = addressRepository.save(updateAddress);

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setAddress(address);
        customerAddress.setCustomer(customer);
        customerAddress.setStatus(StatusEnum.ACTIVE.toString());
        customerAddressRepository.save(customerAddress);
      }
    }
    
    List<IdentifyDocument> updateIdentifyDocuments = updateCustomerPOJO.getIndetifyCards();
    for(IdentifyDocument updateIdentifyDocument : updateIdentifyDocuments) {
      if(updateIdentifyDocument.getId() != null) {
        IdentifyDocument identifyDocument = identifyDocumentRepository.findOne(updateIdentifyDocument.getId());
        if(identifyDocument == null) {
          throw new DataNotFoundException("Address could not be found. Id: " + updateIdentifyDocument.getId());
        }
        identifyDocument.setDateOfIssue(updateIdentifyDocument.getDateOfIssue());
        identifyDocument.setDateOfExpiry(updateIdentifyDocument.getDateOfExpiry());
        identifyDocument.setPlaceOfIssue(updateIdentifyDocument.getPlaceOfIssue());
        identifyDocumentRepository.save(identifyDocument);
      } else {
        IdentifyDocument identifyDocument = identifyDocumentRepository.save(updateIdentifyDocument);

        CustomerIdentifyDocument customerIdentifyDocument = new CustomerIdentifyDocument();
        customerIdentifyDocument.setIdentifyDocument(identifyDocument);
        customerIdentifyDocument.setCustomer(customer);
        customerIdentifyDocumentsRepository.save(customerIdentifyDocument);
      }
    }
    
    historyVO.getNext().put(StringConstant.NEXT, JsonUtils.objectToJsonString(new CustomerVO(customer)));

    // update history
    CustomerHistory customerHistory = new CustomerHistory();
    customerHistory.setCustomer(customer);
    customerHistory.setType(customerHistoryTypeRepository.findOne(CustomerHistoryType.UPDATED));
    customerHistory.setCreatedBy(SCMSSyncDetail.SCMS_SYNC);
    customerHistory.setDetails(JsonUtils.objectToJsonString(historyVO));
    customerHistoryRepository.save(customerHistory);
    
    return customer;
  }
}
