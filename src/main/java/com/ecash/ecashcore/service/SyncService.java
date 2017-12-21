package com.ecash.ecashcore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.AccountTypeEnum;
import com.ecash.ecashcore.enums.AddressTypeEnum;
import com.ecash.ecashcore.enums.CardTypeEnum;
import com.ecash.ecashcore.enums.CurrencyCodeEnum;
import com.ecash.ecashcore.enums.CustomerTypeEnum;
import com.ecash.ecashcore.enums.EWalletTypeEnum;
import com.ecash.ecashcore.enums.IdentifyDocumentTypeEnum;
import com.ecash.ecashcore.enums.PlanTypeEnum;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.CustomerAddress;
import com.ecash.ecashcore.model.cms.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.cms.IdentifyDocument;
import com.ecash.ecashcore.model.cms.Organization;
import com.ecash.ecashcore.model.cms.SCMSSync;
import com.ecash.ecashcore.model.cms.Wallet;
import com.ecash.ecashcore.model.wallet.EWallet;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.AccountTypeRepository;
import com.ecash.ecashcore.repository.AddressRepository;
import com.ecash.ecashcore.repository.AddressTypeRepository;
import com.ecash.ecashcore.repository.CardHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.CardTypeRepository;
import com.ecash.ecashcore.repository.CurrencyCodeRepository;
import com.ecash.ecashcore.repository.CustomerAddressRepository;
import com.ecash.ecashcore.repository.CustomerIdentifyDocumentsRepository;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.ecash.ecashcore.repository.CustomerTypeRepository;
import com.ecash.ecashcore.repository.EWalletRepository;
import com.ecash.ecashcore.repository.EWalletTypeRepository;
import com.ecash.ecashcore.repository.IdentifyDocumentRepository;
import com.ecash.ecashcore.repository.IdentifyDocumentTypeRepository;
import com.ecash.ecashcore.repository.OrganizationRepository;
import com.ecash.ecashcore.repository.PlanRepository;
import com.ecash.ecashcore.repository.SCMSSyncRepository;
import com.ecash.ecashcore.repository.WalletRepository;
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
  EWalletTypeRepository eWalletTypeRepository;

  @Autowired
  EWalletRepository eWalletRepository;

  private static final String SWT = "SWT";
  private static final String DEFAULT = "DEFAULT";

  public void sync(List<SyncVO> syncDatas) {
    for (SyncVO syncData : syncDatas) {
      SCMSSync scmsSync = syncData.getSCMSSync();
      Optional<SCMSSync> oldSync = Optional.ofNullable(scmsSyncRepository.findBySyncCode(scmsSync.getSyncCode()));

      if (!oldSync.isPresent()) {
        scmsSyncRepository.save(scmsSync);

        Organization organization = syncOrg(syncData.getOrganization());

        Customer customer = syncCustomer(syncData.getCustomer(), organization);

        // TODO: check unique or delete old address
        syncAddress(syncData.getCustomerAddress(), customer);

        syncIdentifyCard(syncData.getIdentifyCard(), customer);
        syncPassportCard(syncData.getPassportCard(), customer);

        Account account = syncAccount(syncData.getAccount(), customer);

        syncCard(syncData.getCard(), account);
      }
    }
  }

  private Card syncCard(Card syncCard, Account account) {
    Card card = cardRepository.findByCardCode(syncCard.getCardCode());
    Wallet wallet = null;
    EWallet eWallet = null;
    if (card != null) {
      card.setEffectiveDate(syncCard.getEffectiveDate());
      card.setExpiryDate(syncCard.getExpiryDate());
      card.setStatus(syncCard.getStatus());
    } else {
      card = syncCard;
      card.setAccount(account);

      card.setCardType(cardTypeRepository.findByTypeCode(CardTypeEnum.DEFAULT.toString()));

      // create wallet
      // TODO: filled missing fields
      wallet = new Wallet();
      wallet.setProvider(SWT);
      wallet.setType(DEFAULT);
      wallet.setCard(card);

      eWallet = new EWallet();
      eWallet.setTypeCode(eWalletTypeRepository.findByTypeCode(EWalletTypeEnum.DEFAULT.toString()));
    }

    cardRepository.save(card);
    if (wallet != null & eWallet != null) {
      eWalletRepository.save(eWallet);
      
//      wallet.set
      walletRepository.save(wallet);
    }
    return card;
  }

  private Address syncAddress(Address address, Customer customer) {
    address.setAddressType(addressTypeRepository.findByTypeCode(AddressTypeEnum.DEFAULT.toString()));
    addressRepository.save(address);

    CustomerAddress customerAddress = new CustomerAddress();
    customerAddress.setAddress(address);
    customerAddress.setCustomer(customer);
    customerAddressRepository.save(customerAddress);

    return address;
  }

  private Account syncAccount(Account syncAccount, Customer customer) {
    List<Account> accounts = customer.getAccounts();
    Account account = null;
    if (accounts == null || accounts.isEmpty()) {
      account = syncAccount;
      account.setCustomer(customer);

      account.setAccountType(accountTypeRepository.findByTypeCode(AccountTypeEnum.DEFAULT.toString()));
      account.setPlan(planRepository.findbyPlanType(PlanTypeEnum.DEFAULT.toString()));
      account.setCurrencyCode(currencyCodeRepository.findOne(CurrencyCodeEnum.VND.toString()));

      accountRepository.save(account);
    } else {
      account = accounts.get(0);
    }

    return account;
  }

  private IdentifyDocument syncIdentifyDocument(IdentifyDocument syncIdentifyCard, Customer customer,
      IdentifyDocumentTypeEnum typeEnum) {
    IdentifyDocument identifyDocument = identifyDocumentRepository.findByNumber(syncIdentifyCard.getNumber());
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

    return identifyDocument;
  }

  private IdentifyDocument syncIdentifyCard(IdentifyDocument syncIdentifyCard, Customer customer) {
    return syncIdentifyDocument(syncIdentifyCard, customer, IdentifyDocumentTypeEnum.IDENTIFY_CARD);
  }

  private IdentifyDocument syncPassportCard(IdentifyDocument syncPassportCard, Customer customer) {
    return syncIdentifyDocument(syncPassportCard, customer, IdentifyDocumentTypeEnum.PASSPORT_CARD);
  }

  private Customer syncCustomer(Customer syncCustomer, Organization organization) {
    Customer customer = customerRepository.findByScmsMemberCode(syncCustomer.getScmsMemberCode());
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
    } else {
      customer = syncCustomer;

      // Set default customer type
      customer.setCustomerType(customerTypeRepository.findByTypeCode(CustomerTypeEnum.DEFAULT.toString()));
    }
    customer.setOrganization(organization);

    return customerRepository.save(customer);
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
