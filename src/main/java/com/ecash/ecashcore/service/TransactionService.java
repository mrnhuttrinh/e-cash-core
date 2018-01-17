package com.ecash.ecashcore.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.model.cms.Transaction;
import com.ecash.ecashcore.repository.TransactionDetailRepository;
import com.ecash.ecashcore.repository.TransactionRepository;
import com.ecash.ecashcore.repository.TransactionTypeRepository;
import com.ecash.ecashcore.vo.TransactionVO;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class TransactionService {

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  TransactionTypeRepository transactionTypeRepository;

  @Autowired
  TransactionDetailRepository transactionDetailRepository;

  @Autowired
  ModelMapper modelMapper;

  public Iterable<Transaction> findAll(Predicate predicate, Pageable pageable) {
    return transactionRepository.findAll(predicate, pageable);
  }

  public List<TransactionVO> findTransactionByDateBetween(Date fromDate, Date toDate) {
    List<TransactionVO> listTransactionVO = new ArrayList<>();
    List<Transaction> temp = transactionRepository.findByDateBetweenAndCardIsNotNull(fromDate,
        toDate);
    temp.stream().forEach(transaction -> {
      listTransactionVO.add(modelMapper.map(transaction, TransactionVO.class));
    });
    return listTransactionVO;
  }

  public List<TransactionVO> findMerchantTransactionByDateBetween(Date fromDate, Date toDate) {
    List<TransactionVO> listTransactionVO = new ArrayList<>();
    List<Transaction> temp = transactionRepository.findByDateBetween(fromDate,
        toDate);
    temp.stream().filter(tran -> tran.getTransactionDetail() != null).forEach(transaction -> {
      listTransactionVO.add(modelMapper.map(transaction, TransactionVO.class));
    });
    return listTransactionVO;
  }
}
