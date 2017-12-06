package com.ecash.ecashcore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.CardStatusEnum;
import com.ecash.ecashcore.enums.HistoryTypeEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.EcashException;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.CardHistory;
import com.ecash.ecashcore.model.HistoryType;
import com.ecash.ecashcore.repository.CardHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.HistoryTypeRepository;
import com.ecash.ecashcore.util.JsonUtils;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.request.UpdateCardStatusRequestVO;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class CardService {

  @Autowired
  CardHistoryRepository cardHistoryRepository;

  @Autowired
  HistoryTypeRepository historyTypeRepository;

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
    if (!card.getStatus().equals(StatusEnum.ACTIVE.getValue())) {
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

  public Iterable<Card> findAll(Predicate predicate, Pageable pageable) {
    return cardRepository.findAll(predicate, pageable);
  }
  
}
