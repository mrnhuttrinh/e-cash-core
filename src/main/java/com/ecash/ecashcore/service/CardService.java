package com.ecash.ecashcore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.CardStatusEnum;
import com.ecash.ecashcore.enums.HistoryTypeEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.EcashException;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.AccountCard;
import com.ecash.ecashcore.model.Address;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.CardHistory;
import com.ecash.ecashcore.model.Customer;
import com.ecash.ecashcore.model.CustomerAddress;
import com.ecash.ecashcore.model.CustomerIdentifyDocument;
import com.ecash.ecashcore.model.HistoryType;
import com.ecash.ecashcore.model.IdentifyDocument;
import com.ecash.ecashcore.model.Organization;
import com.ecash.ecashcore.model.SCMSSync;
import com.ecash.ecashcore.repository.AccountCardRepository;
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
import com.ecash.ecashcore.util.JsonUtils;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.InputCardVO;
import com.ecash.ecashcore.vo.request.UpdateCardStatusRequestVO;

@Service
@Transactional
public class CardService {

  @Autowired
  SCMSSyncRepository scmsSyncRepository;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  CardRepository cardRepository;

  @Autowired
  AccountCardRepository accountCardRepository;

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

  public void saveCardInput(List<InputCardVO> inputCards) {
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
        cardRepository.save(card);

        AccountCard accountCard = new AccountCard();
        accountCard.setAccount(account);
        accountCard.setCard(card);
        accountCardRepository.save(accountCard);
      }
    }
  }

  public Card updateCardStatus(UpdateCardStatusRequestVO request) {
    // validate input
    if (request == null || request.getCard() == null || StringUtils.isNullOrEmpty(request.getCard().getNumber())
        || StringUtils.isNullOrEmpty(request.getStatus())) {
      throw new InvalidInputException("Missing request information.");
    }

    // find card and card status
    Card card = identifyCard(request.getCard().getNumber());
    CardStatusEnum status = validateCardStatus(request.getStatus());

    if (card.getStatus().equalsIgnoreCase(status.getValue())) {
      throw new EcashException("Error: card status is " + status.getValue() + " already.");
    }

    // save card history
    HistoryType historyType = historyTypeRepository.findOne(HistoryTypeEnum.UPDATED.toString());
    CardHistory cardHistory = new CardHistory(card, historyType, JsonUtils.objectToJsonString(card));
    cardHistoryRepository.save(cardHistory);

    // update
    card.setStatus(status.getValue());
    cardRepository.save(card);

    return card;
  }

  public Card identifyValidCard(String cardNumber) {
    // check validate card
    Card card = this.identifyCard(cardNumber);
    if (card.getStatus().equals(StatusEnum.ACTIVE.getValue())) {
      throw new ValidationException("Card is inactive.");
    }

    return card;
  }

  public Card identifyCard(String cardNumber) {
    Optional<Card> cardOptional = Optional.ofNullable(cardRepository.findByCardCode(cardNumber));
    if (!cardOptional.isPresent()) {
      throw new ValidationException("Card number is not valid or not exist.");
    }

    return cardOptional.get();
  }

  private CardStatusEnum validateCardStatus(String status) {
    CardStatusEnum cardStatusEnum = CardStatusEnum.findByStatus(status);
    if (cardStatusEnum == null) {
      throw new ValidationException("Card status is not valid.");
    }
    return cardStatusEnum;
  }
}
