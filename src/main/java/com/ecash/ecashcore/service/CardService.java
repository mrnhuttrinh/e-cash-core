package com.ecash.ecashcore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.constants.StringConstant;
import com.ecash.ecashcore.enums.CardStatusEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.EcashException;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.CardHistory;
import com.ecash.ecashcore.model.cms.CardHistoryType;
import com.ecash.ecashcore.repository.CardHistoryRepository;
import com.ecash.ecashcore.repository.CardHistoryTypeRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.util.JsonUtils;
import com.ecash.ecashcore.util.ObjectUtils;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.HistoryVO;
import com.ecash.ecashcore.vo.request.UpdateCardStatusRequestVO;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class CardService {

  @Autowired
  CardHistoryRepository cardHistoryRepository;

  @Autowired
  CardHistoryTypeRepository cardHistoryTypeRepository;

  @Autowired
  CardRepository cardRepository;

  public Card updateCardStatus(UpdateCardStatusRequestVO request) {
    // validate input
    if (request == null || request.getCard() == null || StringUtils.isNullOrEmpty(request.getCard().getNumber())
        || StringUtils.isNullOrEmpty(request.getStatus())) {
      throw new InvalidInputException("Missing request information.");
    }

    // find card and card status
    Card card = identifyCard(request.getCard().getNumber());
    CardStatusEnum status = validateCardStatus(request.getStatus());

    if (card.getStatus().equalsIgnoreCase(status.toString())) {
      throw new EcashException("Error: card status is " + status.toString() + " already.");
    }
    
    validateActiveCardCode(card);

    // save card history
    CardHistoryType historyType = cardHistoryTypeRepository.findOne(CardHistoryType.UPDATED);
    HistoryVO history = new HistoryVO();

    history.getPrevious().put(StringConstant.STATUS, card.getStatus());
    history.getNext().put(StringConstant.STATUS, status.toString());

    CardHistory cardHistory = new CardHistory(card, historyType, JsonUtils.objectToJsonString(history));
    cardHistoryRepository.save(cardHistory);

    // update
    card.setStatus(status.toString());
    cardRepository.save(card);

    return card;
  }

  public Card identifyValidCardByCardCode(String cardCode) {
    // check validate card
    List<Card> cards = cardRepository.findByCardCodeAndStatus(cardCode, StatusEnum.ACTIVE.toString());

    Card result = null;
    if (!cards.isEmpty()) {
      if (cards.size() > 1) {
        throw new ValidationException("There are more than 1 active card code.");
      }
      result = cards.get(0);
    }

    if (result == null) {
      throw new ValidationException("Card is inactive.");
    }

    return result;
  }

  public Card identifyCard(String cardNumber) {
    Optional<Card> cardOptional = Optional.ofNullable(cardRepository.findOne(cardNumber));
    if (!cardOptional.isPresent()) {
      throw new ValidationException("Card number is not valid or not exist.");
    }

    return cardOptional.get();
  }

  public void validateActiveCardCode(Card card) {
    if (CardStatusEnum.ACTIVE.toString().equals(card.getStatus())) {
      List<Card> cards = cardRepository.findByCardCodeAndStatus(card.getCardCode(), CardStatusEnum.ACTIVE.toString());
      for (Card e : cards) {
        if (e.getCardNumber() != card.getCardNumber()) {
          throw new ValidationException("There are more than 1 active card code.");
        }
      }
    }
  }

  private CardStatusEnum validateCardStatus(String status) {
    CardStatusEnum cardStatusEnum = CardStatusEnum.findByStatus(status);
    if (cardStatusEnum == null) {
      throw new ValidationException("Card status is not valid.");
    }
    return cardStatusEnum;
  }

  public Iterable<Card> findAll(Predicate predicate, Pageable pageable) {
    Page<Card> result = cardRepository.findAll(predicate, pageable);
    for(Card c : result) {
      c.setAccount(ObjectUtils.clone(c.getAccount(), Account.class));
    }
    return result;
  }
}
