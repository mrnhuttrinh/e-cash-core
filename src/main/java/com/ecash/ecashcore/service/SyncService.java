package com.ecash.ecashcore.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.AccountTypeEnum;
import com.ecash.ecashcore.enums.AddressTypeEnum;
import com.ecash.ecashcore.enums.CardTypeEnum;
import com.ecash.ecashcore.enums.CurrencyCodeEnum;
import com.ecash.ecashcore.enums.CustomerTypeEnum;
import com.ecash.ecashcore.enums.IdentifyDocumentTypeEnum;
import com.ecash.ecashcore.enums.PlanTypeEnum;
import com.ecash.ecashcore.enums.SCMSSyncTargetEnum;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.AccountHistory;
import com.ecash.ecashcore.model.cms.AccountHistoryType;
import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.CardHistory;
import com.ecash.ecashcore.model.cms.CardHistoryType;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.CustomerAddress;
import com.ecash.ecashcore.model.cms.CustomerHistory;
import com.ecash.ecashcore.model.cms.CustomerHistoryType;
import com.ecash.ecashcore.model.cms.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.Organization;
import com.ecash.ecashcore.model.cms.SCMSSync;
import com.ecash.ecashcore.model.cms.SCMSSyncDetail;
import com.ecash.ecashcore.model.cms.User;
import com.ecash.ecashcore.model.cms.Wallet;
import com.ecash.ecashcore.repository.AccountHistoryRepository;
import com.ecash.ecashcore.repository.AccountHistoryTypeRepository;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.AccountTypeRepository;
import com.ecash.ecashcore.repository.AddressRepository;
import com.ecash.ecashcore.repository.AddressTypeRepository;
import com.ecash.ecashcore.repository.CardHistoryRepository;
import com.ecash.ecashcore.repository.CardHistoryTypeRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.CardTypeRepository;
import com.ecash.ecashcore.repository.CurrencyCodeRepository;
import com.ecash.ecashcore.repository.CustomerAddressRepository;
import com.ecash.ecashcore.repository.CustomerHistoryRepository;
import com.ecash.ecashcore.repository.CustomerHistoryTypeRepository;
import com.ecash.ecashcore.repository.CustomerIdentifyDocumentsRepository;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.ecash.ecashcore.repository.CustomerTypeRepository;
import com.ecash.ecashcore.repository.IdentifyDocumentRepository;
import com.ecash.ecashcore.repository.IdentifyDocumentTypeRepository;
import com.ecash.ecashcore.repository.OrganizationRepository;
import com.ecash.ecashcore.repository.PlanRepository;
import com.ecash.ecashcore.repository.SCMSSyncDetailRepository;
import com.ecash.ecashcore.repository.SCMSSyncRepository;
import com.ecash.ecashcore.repository.UserRepository;
import com.ecash.ecashcore.repository.WalletRepository;
import com.ecash.ecashcore.util.DateTimeUtils;
import com.ecash.ecashcore.vo.SyncVO;

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
  CardHistoryTypeRepository cardHistoryTypeRepository;

  @Autowired
  WalletRepository walletRepository;

  @Autowired
  CustomerTypeRepository customerTypeRepository;

  @Autowired
  AddressTypeRepository addressTypeRepository;

  @Autowired
  IdentifyDocumentTypeRepository identifyDocumentTypeRepository;

  @Autowired
  AccountTypeRepository accountTypeRepository;

  @Autowired
  PlanRepository planRepository;

  @Autowired
  CurrencyCodeRepository currencyCodeRepository;

  @Autowired
  WalletService walletService;

  @Autowired
  RoleService roleService;

  @Autowired
  UserRepository userRepository;

  @Autowired
  SCMSSyncDetailRepository scmsSyncDetailRepository;

  @Autowired
  CustomerHistoryTypeRepository customerHistoryTypeRepository;

  @Autowired
  CustomerHistoryRepository customerHistoryRepository;

  @Autowired
  AccountHistoryRepository accountHistoryRepository;

  @Autowired
  AccountHistoryTypeRepository accountHistoryTypeRepository;

  public void sync(List<SyncVO> syncDatas) {
    for (SyncVO syncData : syncDatas) {
      SCMSSync scmsSync = syncData.getSCMSSync();
      Optional<SCMSSync> oldSync = Optional.ofNullable(scmsSyncRepository.findBySyncCode(scmsSync.getSyncCode()));

      if (!oldSync.isPresent()) {
        scmsSyncRepository.save(scmsSync);

        Organization organization = syncOrg(syncData.getOrganization());

        Customer customer = syncCustomer(syncData.getCustomer(), organization, syncData);

        syncAddress(syncData.getCustomerAddress(), customer, syncData);

        syncIdentifyCard(syncData.getIdentifyCard(), customer, syncData);
        syncPassportCard(syncData.getPassportCard(), customer, syncData);

        Account account = syncAccount(syncData.getAccount(), customer, syncData);

        syncCard(syncData.getCard(), account, syncData);

        syncUser(customer);
      }
    }
  }

  private void syncUser(Customer customer) {
    User user = userRepository.findByUsername(customer.getScmsMemberCode());
    if (user == null) {
      user = new User();
      user.setEmail(customer.getEmail());
      user.setEnabled(true);
      user.setFirstName(customer.getFirstName());
      user.setLastName(customer.getLastName());
      user.setUsername(customer.getScmsMemberCode());
      // default password is date of birth with format: yyyyMMdd
      PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      user.setPassword(DateTimeUtils.datetoString(customer.getDateOfBirth(), DateTimeUtils.DEFAULT_FORMAT_SHORT_DATE));
      user.encodePassword(passwordEncoder);
      user.setRoles(Arrays.asList(roleService.getRoleUSER()));

      userRepository.save(user);
    }
  }

  private Card syncCard(Card syncCard, Account account, SyncVO syncData) {
    SCMSSyncDetail scmsSyncDetail = scmsSyncDetailRepository.findByPersonalizationCodeAndTargetObject(
        syncData.getPersonalizationCode(), SCMSSyncTargetEnum.CARD.toString());

    Card card = null;
    if (scmsSyncDetail != null) {
      card = cardRepository.findOne(scmsSyncDetail.getTargetId());
    }
    
    CardHistory cardHistory = null;
    if (card != null) {
      card.setEffectiveDate(syncCard.getEffectiveDate());
      card.setExpiryDate(syncCard.getExpiryDate());
      card.setStatus(syncCard.getStatus());
      
      cardHistory = new CardHistory();
      cardHistory.setCard(card);
      cardHistory.setType(cardHistoryTypeRepository.findOne(CardHistoryType.UPDATED));
    } else {
      card = syncCard;
      card.setAccount(account);
      card.setCardType(cardTypeRepository.findByTypeCode(CardTypeEnum.DEFAULT.toString()));
      Wallet wallet = new Wallet();
      wallet.setCard(card);
      walletService.createWallet(wallet);
      
      cardHistory = new CardHistory();
      cardHistory.setCard(card);
      cardHistory.setType(cardHistoryTypeRepository.findOne(CardHistoryType.CREATED));
    }

    cardRepository.save(card);

    if (scmsSyncDetail == null) {
      scmsSyncDetail = new SCMSSyncDetail(syncData.getPersonalizationCode(), SCMSSyncDetail.SCMS_SYNC,
          SCMSSyncTargetEnum.CARD.toString(), card.getCardNumber());
    }
    
    if(cardHistory != null) {
      cardHistoryRepository.save(cardHistory);
    }

    scmsSyncDetailRepository.save(scmsSyncDetail);

    return card;
  }

  private Address syncAddress(Address syncAddress, Customer customer, SyncVO syncData) {

    List<Address> addresses = addressRepository.findByLine1AndLine2AndCountry(syncAddress.getLine1(),
        syncAddress.getLine2(), syncAddress.getCountry());

    Address address = null;
    boolean isContain = true;
    if (addresses.isEmpty()) {
      address = syncAddress;

      address.setAddressType(addressTypeRepository.findByTypeCode(AddressTypeEnum.DEFAULT.toString()));
      addressRepository.save(address);

      isContain = false;
    } else {
      address = addresses.get(0);

      isContain = address.getCustomers().stream().anyMatch(t -> t.getId() == customer.getId());
    }

    if (!isContain) {
      CustomerAddress customerAddress = new CustomerAddress();
      customerAddress.setAddress(address);
      customerAddress.setCustomer(customer);
      customerAddressRepository.save(customerAddress);
    }

    return address;
  }

  private Account syncAccount(Account syncAccount, Customer customer, SyncVO syncData) {
    List<Account> accounts = customer.getAccounts();
    Account account = null;
    if (accounts == null || accounts.isEmpty()) {
      account = syncAccount;
      account.setCustomer(customer);

      account.setAccountType(accountTypeRepository.findByTypeCode(AccountTypeEnum.DEFAULT.toString()));
      account.setPlan(planRepository.findbyPlanType(PlanTypeEnum.DEFAULT.toString()));
      account.setCurrencyCode(currencyCodeRepository.findOne(CurrencyCodeEnum.VND.toString()));

      accountRepository.save(account);

      AccountHistory accountHistory = new AccountHistory();
      accountHistory.setAccount(account);
      accountHistory.setType(accountHistoryTypeRepository.findOne(AccountHistoryType.CREATED));

      accountHistoryRepository.save(accountHistory);
    } else {
      account = accounts.get(0);
    }

    return account;
  }

  private IdentifyDocument syncIdentifyDocument(IdentifyDocument syncIdentifyCard, Customer customer,
      IdentifyDocumentTypeEnum typeEnum, SCMSSyncTargetEnum syncTarget, SyncVO syncData) {
    SCMSSyncDetail scmsSyncDetail = scmsSyncDetailRepository
        .findByPersonalizationCodeAndTargetObject(syncData.getPersonalizationCode(), syncTarget.toString());

    IdentifyDocument identifyDocument = null;
    if (scmsSyncDetail != null) {
      identifyDocument = identifyDocumentRepository.findOne(scmsSyncDetail.getTargetId());
    }

    CustomerIdentifyDocument customerIdentifyCard = null;
    if (identifyDocument != null) {
      identifyDocument.setDateOfIssue(syncIdentifyCard.getDateOfIssue());
      identifyDocument.setDateOfExpiry(syncIdentifyCard.getDateOfExpiry());
      identifyDocument.setPlaceOfIssue(syncIdentifyCard.getPlaceOfIssue());
    } else {
      identifyDocument = syncIdentifyCard;

      // create link
      customerIdentifyCard = new CustomerIdentifyDocument();
      customerIdentifyCard.setCustomer(customer);
      customerIdentifyCard.setIdentifyDocument(identifyDocument);

      identifyDocument.setIdentifyDocumentType(identifyDocumentTypeRepository.findByTypeCode(typeEnum.toString()));
    }

    identifyDocumentRepository.save(identifyDocument);
    if (customerIdentifyCard != null) {
      customerIdentifyDocumentsRepository.save(customerIdentifyCard);
    }

    if (scmsSyncDetail == null) {
      scmsSyncDetail = new SCMSSyncDetail(syncData.getPersonalizationCode(), SCMSSyncDetail.SCMS_SYNC,
          syncTarget.toString(), identifyDocument.getId());
    }

    scmsSyncDetailRepository.save(scmsSyncDetail);

    return identifyDocument;
  }

  private IdentifyDocument syncIdentifyCard(IdentifyDocument syncIdentifyCard, Customer customer, SyncVO syncData) {
    return syncIdentifyDocument(syncIdentifyCard, customer, IdentifyDocumentTypeEnum.IDENTIFY_CARD,
        SCMSSyncTargetEnum.IDENTIFY_CARD, syncData);
  }

  private IdentifyDocument syncPassportCard(IdentifyDocument syncPassportCard, Customer customer, SyncVO syncData) {
    return syncIdentifyDocument(syncPassportCard, customer, IdentifyDocumentTypeEnum.PASSPORT_CARD,
        SCMSSyncTargetEnum.PASSPORT_CARD, syncData);
  }

  private Customer syncCustomer(Customer syncCustomer, Organization organization, SyncVO syncData) {
    SCMSSyncDetail scmsSyncDetail = scmsSyncDetailRepository.findByPersonalizationCodeAndTargetObject(
        syncData.getPersonalizationCode(), SCMSSyncTargetEnum.CUSTOMER.toString());

    Customer customer = null;
    if (scmsSyncDetail != null) {
      customer = customerRepository.findOne(scmsSyncDetail.getTargetId());
    }

    CustomerHistory customerHistory = null;
    if (customer != null) {
      customer.setScmsMemberCode(syncCustomer.getScmsMemberCode());
      customer.setFirstName(syncCustomer.getFirstName());
      customer.setLastName(syncCustomer.getLastName());
      customer.setGender(syncCustomer.getGender());
      customer.setDateOfBirth(syncCustomer.getDateOfBirth());
      customer.setPhone1(syncCustomer.getPhone1());
      customer.setPhone1(syncCustomer.getPhone2());
      customer.setEmail(syncCustomer.getEmail());
      customer.setDateBecameCustomer(syncCustomer.getDateBecameCustomer());
      customer.setCountryCode(syncCustomer.getCountryCode());
      customer.setOccupation(syncCustomer.getOccupation());
      customer.setTitle(syncCustomer.getTitle());
      customer.setPosition(syncCustomer.getPosition());

      // update history
      customerHistory = new CustomerHistory();
      customerHistory.setCustomer(customer);
      customerHistory.setType(customerHistoryTypeRepository.findOne(CustomerHistoryType.UPDATED));
    } else {
      customer = syncCustomer;

      // Set default customer type
      customer.setCustomerType(customerTypeRepository.findByTypeCode(CustomerTypeEnum.DEFAULT.toString()));

      // update history
      customerHistory = new CustomerHistory();
      customerHistory.setCustomer(customer);
      customerHistory.setType(customerHistoryTypeRepository.findOne(CustomerHistoryType.CREATED));
    }
    customer.setOrganization(organization);
    customerRepository.save(customer);

    if (scmsSyncDetail == null) {
      scmsSyncDetail = new SCMSSyncDetail(syncData.getPersonalizationCode(), SCMSSyncDetail.SCMS_SYNC,
          SCMSSyncTargetEnum.CUSTOMER.toString(), customer.getId());
    }

    if (customerHistory != null) {
      customerHistoryRepository.save(customerHistory);
    }

    scmsSyncDetailRepository.save(scmsSyncDetail);

    return customer;
  }

  private Organization syncOrg(Organization syncOrg) {
    Organization org = organizationRepository.findOne(syncOrg.getId());
    if (org != null) {
      org.setShortName(syncOrg.getShortName());
    } else {
      org = syncOrg;
    }

    return organizationRepository.save(org);
  }
}
