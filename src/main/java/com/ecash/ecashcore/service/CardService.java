package com.ecash.ecashcore.service;

import com.ecash.ecashcore.constants.StringConstant;
import com.ecash.ecashcore.enums.CardStatusEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.exception.EcashException;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.CardHistory;
import com.ecash.ecashcore.model.cms.CardHistoryType;
import com.ecash.ecashcore.repository.CardHistoryRepository;
import com.ecash.ecashcore.repository.CardHistoryTypeRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.util.JsonUtils;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.HistoryVO;
import com.ecash.ecashcore.vo.request.UpdateCardStatusRequestVO;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

  public Card identifyValidCard(String cardNumber) {
    // check validate card
    Card card = this.identifyCard(cardNumber);
    if (!card.getStatus().equals(StatusEnum.ACTIVE.toString())) {
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
