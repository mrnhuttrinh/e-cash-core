package com.ecash.ecashcore.repository;

import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.QCard;

public interface CardRepository extends BaseQuerydslRepository<Card, String, QCard> {

  Card findByCardCode(String cardCode);

  Card findByCardNumber(String cardNumber);
}
