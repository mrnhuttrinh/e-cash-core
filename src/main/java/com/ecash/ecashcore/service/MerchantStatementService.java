package com.ecash.ecashcore.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.MerchantStatusEnum;
import com.ecash.ecashcore.enums.TransactionTypeEnum;
import com.ecash.ecashcore.model.cms.MerchantStatement;
import com.ecash.ecashcore.model.cms.MerchantStatementDetail;
import com.ecash.ecashcore.repository.CurrencyCodeRepository;
import com.ecash.ecashcore.repository.MerchantRepository;
import com.ecash.ecashcore.repository.MerchantStatementDetailRepository;
import com.ecash.ecashcore.repository.MerchantStatementRepository;
import com.ecash.ecashcore.repository.TransactionTypeRepository;
import com.ecash.ecashcore.util.DateTimeUtils;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.MerchantStatementVO;

@Service
@Transactional()
public class MerchantStatementService
{
  @Autowired
  private MerchantStatementRepository merchantStatementRepository;

  @Autowired
  private MerchantStatementDetailRepository merchantStatementDetailRepository;

  @Autowired
  private MerchantRepository merchantRepository;

  @Autowired
  private CurrencyCodeRepository currencyCodeRepository;

  @Autowired
  private TransactionTypeRepository transactionTypeRepository;

  @Autowired
  ModelMapper modelMapper;

  public MerchantStatementVO upsertMerchantStatement(MerchantStatementVO merchantStatementVO)
  {

    // Check if MerchantStatement existed:
    List<MerchantStatement> temp = merchantStatementRepository
        .findByMerchantIdAndDueDateBetween(merchantStatementVO.getMerchantId(),
            DateTimeUtils.convertToStartOfDay(merchantStatementVO.getDueDate()),
            DateTimeUtils.convertToEndOfDay(merchantStatementVO.getDueDate()));
    MerchantStatement merchantStatement = null;
    MerchantStatementDetail merchantStatementDetail = null;
    if (temp.isEmpty())
    {
      merchantStatement = new MerchantStatement();
      // Save merchantStatement
      merchantStatement
          .setMerchant(merchantRepository.findOne(merchantStatementVO.getMerchantId()));
      merchantStatement.setCreatedAt(new Date());
      merchantStatement.setCreatedBy(merchantStatementVO.getCreatedBy());
      merchantStatement
          .setCurrencyCode(
              currencyCodeRepository.findByCode(merchantStatementVO.getCurrencyCode()));
      merchantStatement.setDueDate(merchantStatementVO.getDueDate());
      merchantStatement.setUpdatedAt(new Date());
      merchantStatement.setClosingAmount(merchantStatementVO.getClosingAmount());
      // Set opening amount:
      Optional<MerchantStatement> mStatementPrevious = Optional
          .ofNullable(merchantStatementRepository
              .findFirstByMerchantIdOrderByDueDateDesc(merchantStatementVO.getMerchantId()));
      if (mStatementPrevious.isPresent())
      {
        merchantStatement.setOpeningAmount(mStatementPrevious.get().getClosingAmount());
      }
      else
      {
        merchantStatement.setOpeningAmount(0d);
      }
      merchantStatement.setTotalTransaction(1l);
      merchantStatement.setStatus(MerchantStatusEnum.STORAGE.getName());
    }
    else
    {
      merchantStatement = temp.get(0);
      // Update merchantstatement
      merchantStatement.setClosingAmount(merchantStatement.getClosingAmount()
          + calculateAmount(
              merchantStatementVO.getMerchantStatementDetailVO().getTransactionAmount(),
              merchantStatementVO.getMerchantStatementDetailVO().getTransactionTypeCode(),
              merchantStatementVO.getCardId()));
      merchantStatement.setTotalTransaction(merchantStatement.getTotalTransaction() + 1);
    }
    merchantStatementRepository.save(merchantStatement);
    // Save merchantstatement detail
    merchantStatementDetail = new MerchantStatementDetail();
    merchantStatementDetail.setMerchantStatement(merchantStatement);
    merchantStatementDetail.setCreatedAt(new Date());
    merchantStatementDetail
        .setCreatedBy(merchantStatementVO.getMerchantStatementDetailVO().getCreatedBy());
    merchantStatementDetail
        .setDescription(merchantStatementVO.getMerchantStatementDetailVO().getDescription());
    merchantStatementDetail.setTransactionAmount(
        merchantStatementVO.getMerchantStatementDetailVO().getTransactionAmount());
    merchantStatementDetail.setTransactionDate(
        merchantStatementVO.getMerchantStatementDetailVO().getTransactionDate());
    merchantStatementDetail.setTransactionType(transactionTypeRepository
        .findOne(merchantStatementVO.getMerchantStatementDetailVO().getTransactionTypeCode()));
    merchantStatementDetail.setUpdatedAt(new Date());
    merchantStatementDetailRepository.save(merchantStatementDetail);
    return merchantStatementVO;
  }

  private double calculateAmount(double amount, String transactionType, String cardId)
  {
    double result;
    boolean flag = StringUtils.isNullOrEmpty(cardId);
    switch (TransactionTypeEnum.valueOf(transactionType))
    {
    case DEPOSIT:
      result = flag ? amount : (amount *= -1);
      break;
    case REFUND:
      result = flag ? amount : (amount *= -1);
      break;
    case EXPENSE:
      result = flag ? amount *= -1 : amount;
      break;
    case PAYMENT:
      result = flag ? amount *= -1 : amount;
      break;
    default:
      result = amount;
      break;
    }
    return result;
  }

}
