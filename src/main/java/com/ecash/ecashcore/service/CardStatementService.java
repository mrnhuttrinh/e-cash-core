package com.ecash.ecashcore.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.TransactionTypeEnum;
import com.ecash.ecashcore.model.cms.CardStatement;
import com.ecash.ecashcore.model.cms.CardStatementDetail;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.CardStatementDetailRepository;
import com.ecash.ecashcore.repository.CardStatementRepository;
import com.ecash.ecashcore.repository.CurrencyCodeRepository;
import com.ecash.ecashcore.repository.TransactionTypeRepository;
import com.ecash.ecashcore.util.DateTimeUtils;
import com.ecash.ecashcore.vo.CardStatementVO;

@Service
@Transactional()
public class CardStatementService
{
  @Autowired
  private CardStatementRepository cardStatementRepository;

  @Autowired
  private CardStatementDetailRepository cardStatementDetailRepository;

  @Autowired
  private CardRepository cardRepository;

  @Autowired
  private CurrencyCodeRepository currencyCodeRepository;

  @Autowired
  private TransactionTypeRepository transactionTypeRepository;

  @Autowired
  ModelMapper modelMapper;

  public CardStatement save(CardStatement cardStatement)
  {
    return cardStatementRepository.save(cardStatement);
  }

  public CardStatementVO upsertCardStatement(CardStatementVO cardStatementVO)
  {

    // Check if CardStatement existed:

    List<CardStatement> temp = cardStatementRepository
        .findByCardCardNumberAndDueDateBetween(cardStatementVO.getCard_number(),
            DateTimeUtils.convertToStartOfDay(cardStatementVO.getDueDate()),
            DateTimeUtils.convertToEndOfDay(cardStatementVO.getDueDate()));
    CardStatement cardStatement = null;
    CardStatementDetail cardStatementDetail = null;
    if (temp.isEmpty())
    {
      cardStatement = new CardStatement();
      // Save cardstatement
      cardStatement.setCard(cardRepository.findByCardNumber(cardStatementVO.getCard_number()));
      cardStatement.setCompany(cardStatementVO.getCompany());
      cardStatement.setCreatedAt(new Date());
      cardStatement.setCreatedBy(cardStatementVO.getCreatedBy());
      cardStatement
          .setCurrencyCode(currencyCodeRepository.findByCode(cardStatementVO.getCurrencyCode()));
      cardStatement.setCurrentAmount(cardStatementVO.getCurrentAmount());
      cardStatement.setDueDate(cardStatementVO.getDueDate());
      
      cardStatement.setPaymentAmount(calculatePayment(cardStatementVO.getPaymentAmount(),
          cardStatementVO.getCardStatementDetailVO().getTransactionTypeCode()));
      
      cardStatement.setUpdatedAt(new Date());
      cardStatementRepository.save(cardStatement);
    }
    else
    {
      cardStatement = temp.get(0);
      // Update cardstatement
      cardStatement
          .setPaymentAmount(cardStatement.getPaymentAmount()
              + calculatePayment(cardStatementVO.getPaymentAmount(),
                  cardStatementVO.getCardStatementDetailVO().getTransactionTypeCode()));
      cardStatementRepository.save(cardStatement);
    }
    // Save cardstatement detail
    cardStatementDetail = new CardStatementDetail();
    cardStatementDetail.setCardStatement(cardStatement);
    cardStatementDetail.setCreatedAt(new Date());
    cardStatementDetail.setCreatedBy(cardStatementVO.getCardStatementDetailVO().getCreatedBy());
    cardStatementDetail.setDescription(cardStatementVO.getCardStatementDetailVO().getDescription());
    cardStatementDetail
        .setTransactionAmount(cardStatementVO.getCardStatementDetailVO().getTransactionAmount());
    cardStatementDetail
        .setTransactionDate(cardStatementVO.getCardStatementDetailVO().getTransactionDate());
    cardStatementDetail.setTransactionType(transactionTypeRepository
        .findOne(cardStatementVO.getCardStatementDetailVO().getTransactionTypeCode()));
    cardStatementDetail.setUpdatedAt(new Date());
    cardStatementDetailRepository.save(cardStatementDetail);

    return cardStatementVO;
  }

  private double calculatePayment(double amount, String transactionType)
  {
    double result;
    switch (TransactionTypeEnum.valueOf(transactionType))
    {
    case DEPOSIT:
      result = amount;
      break;
    case REFUND:
      result = amount;
      break;
    case EXPENSE:
      result = amount *= -1;
      break;
    case PAYMENT:
      result = amount *= -1;
      break;
    default:
      result = amount;
      break;
    }
    return result;
  }

}
