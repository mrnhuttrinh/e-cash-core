package com.ecash.ecashcore.service;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.CustomerAddress;
import com.ecash.ecashcore.model.cms.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.Organization;
import com.ecash.ecashcore.model.cms.SCMSSync;
import com.ecash.ecashcore.repository.*;
import com.ecash.ecashcore.vo.InputCardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SyncService {

  @Autowired
  SCMSSyncRepository scmsSyncRepository;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  CardRepository cardRepository;

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  CustomerAddressRepository customerAddressRepository;

  @Autowired
  OrganizationRepository organizationRepository;

  @Autowired
  IdentifyDocumentRepository identifyDocumentRepository;

  @Autowired
  CustomerIdentifyDocumentsRepository customerIdentifyDocumentsRepository;

  @Autowired
  CardTypeRepository cardTypeRepository;

  @Autowired
  CardHistoryRepository cardHistoryRepository;

  public void syncData(List<InputCardVO> inputCards) {
    for (InputCardVO inputCard : inputCards) {
      SCMSSync scmsSync = inputCard.getSCMSSync();
      Optional<SCMSSync> oldSync = Optional.ofNullable(scmsSyncRepository.findBySyncCode(scmsSync.getSyncCode()));

      if (!oldSync.isPresent()) {
        scmsSyncRepository.save(scmsSync);

        Address address = inputCard.getCustomerAddress();
        addressRepository.save(address);

        Organization organization = organizationRepository.save(inputCard.getOrganization());

        Customer customer = inputCard.getCustomer();
        customer.setOrganization(organization);
        customerRepository.save(customer);

        IdentifyDocument identifyCard = inputCard.getIdentifyCard();
        IdentifyDocument passportCard = inputCard.getPassportCard();
        identifyDocumentRepository.save(identifyCard);
        identifyDocumentRepository.save(passportCard);

        CustomerIdentifyDocument customerIdentifyCard = new CustomerIdentifyDocument();
        customerIdentifyCard.setCustomer(customer);
        customerIdentifyCard.setIdentifyDocument(identifyCard);
        customerIdentifyDocumentsRepository.save(customerIdentifyCard);

        CustomerIdentifyDocument customerPassportCard = new CustomerIdentifyDocument();
        customerPassportCard.setCustomer(customer);
        customerPassportCard.setIdentifyDocument(passportCard);
        customerIdentifyDocumentsRepository.save(customerPassportCard);

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setAddress(address);
        customerAddress.setCustomer(customer);
        customerAddressRepository.save(customerAddress);

        Account account = inputCard.getAccount();
        account.setCustomer(customer);
        accountRepository.save(account);

        Card card = inputCard.getCard();
        card.setAccount(account);
        cardRepository.save(card);
      }
    }
  }
}
