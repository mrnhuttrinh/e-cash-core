package com.ecash.ecashcore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.Address;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.CustomerAddress;
import com.ecash.ecashcore.model.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.IdentifyDocument;
import com.ecash.ecashcore.model.Organization;
import com.ecash.ecashcore.model.SCMSSync;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.AddressRepository;
import com.ecash.ecashcore.repository.CardHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.CardTypeRepository;
import com.ecash.ecashcore.repository.CustomerAddressRepository;
import com.ecash.ecashcore.repository.CustomerIdentifyDocumentsRepository;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.ecash.ecashcore.repository.HistoryTypeRepository;
import com.ecash.ecashcore.repository.IdentifyDocumentRepository;
import com.ecash.ecashcore.repository.OrganizationRepository;
import com.ecash.ecashcore.repository.SCMSSyncRepository;
import com.ecash.ecashcore.vo.InputCardVO;

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

  @Autowired
  HistoryTypeRepository historyTypeRepository;

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
