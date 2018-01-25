package com.ecash.ecashcore.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.constants.StringConstant;
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
import com.ecash.ecashcore.util.JsonUtils;
import com.ecash.ecashcore.util.ObjectUtils;
import com.ecash.ecashcore.vo.HistoryVO;
import com.ecash.ecashcore.vo.SyncVO;

@Service
@Transactional
public class SyncService {

  @Autowired
  SCMSSyncRepository scmsSyncRepository;

  @Autowired
  SCMSSyncDetailRepository scmsSyncDetailRepository;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  AccountTypeRepository accountTypeRepository;

  @Autowired
  AccountHistoryRepository accountHistoryRepository;

  @Autowired
  AccountHistoryTypeRepository accountHistoryTypeRepository;

  @Autowired
  CardRepository cardRepository;

  @Autowired
  CardTypeRepository cardTypeRepository;

  @Autowired
  CardHistoryRepository cardHistoryRepository;

  @Autowired
  CardHistoryTypeRepository cardHistoryTypeRepository;

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  AddressTypeRepository addressTypeRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  CustomerTypeRepository customerTypeRepository;

  @Autowired
  CustomerAddressRepository customerAddressRepository;

  @Autowired
  CustomerHistoryTypeRepository customerHistoryTypeRepository;

  @Autowired
  CustomerHistoryRepository customerHistoryRepository;

  @Autowired
  CustomerIdentifyDocumentsRepository customerIdentifyDocumentsRepository;

  @Autowired
  OrganizationRepository organizationRepository;

  @Autowired
  IdentifyDocumentRepository identifyDocumentRepository;

  @Autowired
  IdentifyDocumentTypeRepository identifyDocumentTypeRepository;

  @Autowired
  WalletRepository walletRepository;

  @Autowired
  WalletService walletService;

  @Autowired
  PlanRepository planRepository;

  @Autowired
  CurrencyCodeRepository currencyCodeRepository;

  @Autowired
  RoleService roleService;

  @Autowired
  UserRepository userRepository;

  public void sync(List<SyncVO> syncDatas) {
    for (SyncVO syncData : syncDatas) {
      SCMSSync scmsSync = syncData.getSCMSSync();
      Optional<SCMSSync> oldSync = Optional.ofNullable(scmsSyncRepository.findBySyncCode(scmsSync.getSyncCode()));

      if (!oldSync.isPresent()) {

        Organization organization = syncOrg(syncData.getOrganization());

        Customer customer = syncCustomer(syncData.getCustomer(), organization, syncData);

        syncAddress(syncData.getCustomerAddress(), customer, syncData);

        syncIdentifyCard(syncData.getIdentifyCard(), customer, syncData);
        syncPassportCard(syncData.getPassportCard(), customer, syncData);

        Account account = syncAccount(syncData.getAccount(), customer, syncData);

        syncCard(syncData.getCard(), account, syncData);

        syncUser(customer);

        scmsSyncRepository.save(scmsSync);
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
    CardHistory cardHistory = null;

    if (scmsSyncDetail != null) {
      card = cardRepository.findOne(scmsSyncDetail.getTargetId());
    }

    if (card != null) {
      if (!ObjectUtils.isCardEqual(card, syncCard)) {

        // create history
//        HistoryVO historyVO = new HistoryVO();
//        historyVO.getPrevious().put(StringConstant.PREVIOUS, JsonUtils.objectToJsonString(card));

        // update
        card.setEffectiveDate(syncCard.getEffectiveDate());
        card.setExpiryDate(syncCard.getExpiryDate());
        card.setStatus(syncCard.getStatus());

//        historyVO.getNext().put(StringConstant.NEXT, JsonUtils.objectToJsonString(card));

        // update history
        cardHistory = new CardHistory();
        cardHistory.setCard(card);
        cardHistory.setType(cardHistoryTypeRepository.findOne(CardHistoryType.UPDATED));
        cardHistory.setCreatedBy(SCMSSyncDetail.SCMS_SYNC);
//        cardHistory.setDetails(JsonUtils.objectToJsonString(historyVO));

        cardRepository.save(card);
      }
    } else {
      card = syncCard;
      card.setAccount(account);
      card.setCardType(cardTypeRepository.findByTypeCode(CardTypeEnum.DEFAULT.toString()));

      Wallet wallet = new Wallet();
      wallet.setCard(card);
      walletService.createWallet(wallet);

      // create history
//      HistoryVO historyVO = new HistoryVO();
//      historyVO.getPrevious().put(StringConstant.PREVIOUS, "");
//      historyVO.getNext().put(StringConstant.NEXT, JsonUtils.objectToJsonString(card));

      cardHistory = new CardHistory();
      cardHistory.setCard(card);
      cardHistory.setType(cardHistoryTypeRepository.findOne(CardHistoryType.CREATED));
      cardHistory.setCreatedBy(SCMSSyncDetail.SCMS_SYNC);
//      cardHistory.setDetails(JsonUtils.objectToJsonString(historyVO));

      cardRepository.save(card);
    }

    if (cardHistory != null) {
      cardHistoryRepository.save(cardHistory);
    }

    if (scmsSyncDetail == null) {
      scmsSyncDetail = new SCMSSyncDetail(syncData.getPersonalizationCode(), SCMSSyncDetail.SCMS_SYNC,
          SCMSSyncTargetEnum.CARD.toString(), card.getCardNumber());
    }

    scmsSyncDetailRepository.save(scmsSyncDetail);

    return card;
  }

  private Address syncAddress(Address syncAddress, Customer customer, SyncVO syncData) {
    SCMSSyncDetail scmsSyncDetail = scmsSyncDetailRepository.findByPersonalizationCodeAndTargetObject(
        syncData.getPersonalizationCode(), SCMSSyncTargetEnum.ADDRESS.toString());

    Address address = null;
    CustomerAddress customerAddress = null;

    if (scmsSyncDetail != null) {
      address = addressRepository.findOne(scmsSyncDetail.getTargetId());
    }

    if (address == null) {
      address = syncAddress;

      address.setAddressType(addressTypeRepository.findByTypeCode(AddressTypeEnum.DEFAULT.toString()));
      addressRepository.save(address);

      customerAddress = new CustomerAddress();
      customerAddress.setAddress(address);
      customerAddress.setCustomer(customer);
    } else {
      if (!ObjectUtils.isAddressEqual(address, syncAddress)) {
        address.setCountry(syncAddress.getCountry());
        address.setLine1(syncAddress.getLine1());
        address.setLine2(syncAddress.getLine2());
        addressRepository.save(address);
      }
    }

    if (customerAddress != null) {
      customerAddressRepository.save(customerAddress);
    }

    if (scmsSyncDetail == null) {
      scmsSyncDetail = new SCMSSyncDetail(syncData.getPersonalizationCode(), SCMSSyncDetail.SCMS_SYNC,
          SCMSSyncTargetEnum.ADDRESS.toString(), address.getId());
    }

    scmsSyncDetailRepository.save(scmsSyncDetail);

    return address;
  }

  private Account syncAccount(Account syncAccount, Customer customer, SyncVO syncData) {
    List<Account> accounts = accountRepository.findByCustomer(customer);
    Account account = null;
    if (accounts == null || accounts.isEmpty()) {
      account = syncAccount;
      account.setCustomer(customer);

      account.setAccountType(accountTypeRepository.findByTypeCode(AccountTypeEnum.DEFAULT.toString()));
      account.setPlan(planRepository.findbyPlanType(PlanTypeEnum.DEFAULT.toString()));
      account.setCurrencyCode(currencyCodeRepository.findOne(CurrencyCodeEnum.VND.toString()));

      accountRepository.save(account);

      // create history
//      HistoryVO historyVO = new HistoryVO();
//      historyVO.getPrevious().put(StringConstant.PREVIOUS, "");
//      historyVO.getNext().put(StringConstant.NEXT, JsonUtils.objectToJsonString(account));

      AccountHistory accountHistory = new AccountHistory();
      accountHistory.setAccount(account);
      accountHistory.setType(accountHistoryTypeRepository.findOne(AccountHistoryType.CREATED));
//      accountHistory.setDetails(JsonUtils.objectToJsonString(historyVO));

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
    CustomerIdentifyDocument customerIdentifyCard = null;

    if (scmsSyncDetail != null) {
      identifyDocument = identifyDocumentRepository.findOne(scmsSyncDetail.getTargetId());
    }

    if (identifyDocument != null) {
      if (!ObjectUtils.isIdentifyDocumentEqual(identifyDocument, syncIdentifyCard)) {
        identifyDocument.setDateOfIssue(syncIdentifyCard.getDateOfIssue());
        identifyDocument.setDateOfExpiry(syncIdentifyCard.getDateOfExpiry());
        identifyDocument.setPlaceOfIssue(syncIdentifyCard.getPlaceOfIssue());
        identifyDocumentRepository.save(identifyDocument);
      }
    } else {
      identifyDocument = syncIdentifyCard;

      // create link
      customerIdentifyCard = new CustomerIdentifyDocument();
      customerIdentifyCard.setCustomer(customer);
      customerIdentifyCard.setIdentifyDocument(identifyDocument);

      identifyDocument.setIdentifyDocumentType(identifyDocumentTypeRepository.findByTypeCode(typeEnum.toString()));
      identifyDocumentRepository.save(identifyDocument);
    }

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
    CustomerHistory customerHistory = null;

    if (scmsSyncDetail != null) {
      customer = customerRepository.findOne(scmsSyncDetail.getTargetId());
    }

    if (customer != null) {
      if (!ObjectUtils.isCustomerEqual(customer, syncCustomer)) {

        // create history
//        HistoryVO historyVO = new HistoryVO();
//        historyVO.getPrevious().put(StringConstant.PREVIOUS, JsonUtils.objectToJsonString(customer));

        // update
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

//        historyVO.getNext().put(StringConstant.NEXT, JsonUtils.objectToJsonString(customer));

        // update history
        customerHistory = new CustomerHistory();
        customerHistory.setCustomer(customer);
        customerHistory.setType(customerHistoryTypeRepository.findOne(CustomerHistoryType.UPDATED));
        customerHistory.setCreatedBy(SCMSSyncDetail.SCMS_SYNC);
//        customerHistory.setDetails(JsonUtils.objectToJsonString(historyVO));
      }
    } else {
      customer = syncCustomer;

      // Set default customer type
      customer.setCustomerType(customerTypeRepository.findByTypeCode(CustomerTypeEnum.DEFAULT.toString()));

      // create history
//      HistoryVO historyVO = new HistoryVO();
//      historyVO.getPrevious().put(StringConstant.PREVIOUS, "");
//      historyVO.getNext().put(StringConstant.NEXT, JsonUtils.objectToJsonString(customer));

      // update history
      customerHistory = new CustomerHistory();
      customerHistory.setCustomer(customer);
      customerHistory.setType(customerHistoryTypeRepository.findOne(CustomerHistoryType.CREATED));
      customerHistory.setCreatedBy(SCMSSyncDetail.SCMS_SYNC);
//      customerHistory.setDetails(JsonUtils.objectToJsonString(historyVO));
    }
    
    customer.setOrganization(organization);
    customerRepository.save(customer);

    if (customerHistory != null) {
      customerHistoryRepository.save(customerHistory);
    }

    if (scmsSyncDetail == null) {
      scmsSyncDetail = new SCMSSyncDetail(syncData.getPersonalizationCode(), SCMSSyncDetail.SCMS_SYNC,
          SCMSSyncTargetEnum.CUSTOMER.toString(), customer.getId());
    }

    scmsSyncDetailRepository.save(scmsSyncDetail);

    return customer;
  }

  private Organization syncOrg(Organization syncOrg) {
    Organization org = organizationRepository.findOne(syncOrg.getId());
    if (org != null) {
      if (org.getShortName().equals(syncOrg.getShortName())) {
        return org;
      }
      org.setShortName(syncOrg.getShortName());
    } else {
      org = syncOrg;
    }

    organizationRepository.save(org);

    return org;
  }
}
