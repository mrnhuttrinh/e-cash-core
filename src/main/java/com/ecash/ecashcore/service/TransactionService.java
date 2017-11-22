package com.ecash.ecashcore.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.AccountTypeEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.enums.TransactionTypeEnum;
import com.ecash.ecashcore.exception.EcashException;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.Account;
import com.ecash.ecashcore.model.BalanceHistory;
import com.ecash.ecashcore.model.Card;
import com.ecash.ecashcore.model.MerchantTerminal;
import com.ecash.ecashcore.model.Transaction;
import com.ecash.ecashcore.model.TransactionDetail;
import com.ecash.ecashcore.model.TransactionType;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.BalanceHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.MerchantTerminalRepository;
import com.ecash.ecashcore.repository.TransactionDetailRepository;
import com.ecash.ecashcore.repository.TransactionRepository;
import com.ecash.ecashcore.repository.TransactionTypeRepository;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.ChargeRequestVO;
import com.ecash.ecashcore.vo.DepositRequestVO;
import com.ecash.ecashcore.vo.ExtendedInformationVO;
import com.ecash.ecashcore.vo.ITransactionRequestVO;
import com.ecash.ecashcore.vo.RefundRequestVO;
import com.ecash.ecashcore.vo.TargetAccountVO;

@Service
@Transactional
public class TransactionService {

  @Autowired
  CardRepository cardRepository;

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  BalanceHistoryRepository balanceHistoryRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  TransactionTypeRepository transactionTypeRepository;

  @Autowired
  TransactionDetailRepository transactionDetailRepository;

  @Autowired
  MerchantTerminalRepository merchantTerminalRepository;

  public Transaction chargeRequest(ChargeRequestVO chargeRequest) {

    validateTransactionRequest(chargeRequest);

    Account account = identifyValidAccount(chargeRequest.getCard().getNumber(), chargeRequest.getTargetAccount());

    double remainAmount = account.getCurrentBalance() - chargeRequest.getAmount();
    if (remainAmount < 0) {
      throw new ValidationException("Account don't have enough money.");
    }

    account.setCurrentBalance(remainAmount);
    accountRepository.save(account);

    Date transactionTime = Calendar.getInstance().getTime();

    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account, account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    TransactionType transactionType = transactionTypeRepository.findOne(TransactionTypeEnum.EXPENSE.getName());
    Transaction transaction = new Transaction(account, transactionType, transactionTime, chargeRequest.getAmount());
    transactionRepository.save(transaction);

    ExtendedInformationVO extendedInformation = chargeRequest.getExtendedInformation();
    MerchantTerminal merchantTerminal = identifyValidMerchantTerminal(
        extendedInformation.getAdditionalTerminalInfo().getTerminalId());

    TransactionDetail transactionDetail = new TransactionDetail(transaction,
        extendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(transactionDetail);
    return transaction;
  }

  public Transaction depositRequest(DepositRequestVO depositRequest) {

    // Validate require information
    validateTransactionRequest(depositRequest);

    // Check valid card information
    Account account = identifyValidAccount(depositRequest.getCard().getNumber(), depositRequest.getTargetAccount());

    // Check valid number.
    if (depositRequest.getAmount() <= 0) {
      throw new InvalidInputException("Required information is missing. Missing amount information");
    }

    // Check the last balance value is valid
    if (isOverflowBalance(account.getCurrentBalance(), depositRequest.getAmount())) {
      throw new InvalidInputException("Required information is missing. The disposite amount's too large");
    }

    // Update balance value
    account.setCurrentBalance(account.getCurrentBalance() + depositRequest.getAmount());
    accountRepository.save(account);

    Date transactionTime = Calendar.getInstance().getTime();

    // Record the balance history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account, account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    // Record the transaction
    TransactionType transactionType = transactionTypeRepository.findOne(TransactionTypeEnum.DEPOSIT.getName());
    Transaction transaction = new Transaction(account, transactionType, transactionTime, depositRequest.getAmount());
    transactionRepository.save(transaction);

    // Identify the merchant terminal
    ExtendedInformationVO extendedInformation = depositRequest.getExtendedInformation();
    MerchantTerminal merchantTerminal = identifyValidMerchantTerminal(
        extendedInformation.getAdditionalTerminalInfo().getTerminalId());

    // Record the transaction detail
    TransactionDetail transactionDetail = new TransactionDetail(transaction,
        extendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(transactionDetail);

    return transaction;
  }

  public Transaction refundRequest(RefundRequestVO refundRequest) {

    // Validate transactionId
    String transactionId = refundRequest.getTransactionId();
    if (StringUtils.isNullOrEmpty(transactionId)) {
      throw new InvalidInputException("Required information is missing. The transaction Id is null or empty");
    }

    // Get transaction from database
    final Optional<Transaction> transaction = Optional.ofNullable(transactionRepository.findOne(transactionId));
    if (!transaction.isPresent()) {
      throw new InvalidInputException("Required information is missing. The transaction Id does not exist");
    }

    // Can not support refund transaction
    if (transaction.get().getTransactionType().getTypeCode().equals(TransactionTypeEnum.REFUND.getName())) {
      throw new InvalidInputException("Required information is missing. The transaction type does not support");
    }

    Date transactionTime = Calendar.getInstance().getTime();

    // Check the origin transaction from request terminal

    // Refund account balance
    refundAccountBalance(transaction.get().getaccount(), transaction.get(), transactionTime);

    // Record the transaction
    TransactionType transactionType = transactionTypeRepository.findOne(TransactionTypeEnum.REFUND.getName());
    final Transaction refundTransaction = new Transaction(transaction.get().getaccount(), transactionType,
        transactionTime, transaction.get().getAmount());

    // Record relate transaction
    refundTransaction.setRelatedTransaction(transaction.get());
    transactionRepository.save(refundTransaction);

    // Record the transaction detail
    TransactionDetail transactionDetail = transaction.get().getTransactionDetail();
    final TransactionDetail refundTransactionDetail = new TransactionDetail(refundTransaction, "Terminal canceled",
        transactionDetail.getMerchant());
    transactionDetailRepository.save(refundTransactionDetail);

    return refundTransaction;
  }

  private void refundAccountBalance(final Account account, final Transaction transacion, final Date transactionTime) {

    switch (transacion.getTransactionType().getTypeCode()) {
    case "DEPOSIT":
      account.setCurrentBalance(account.getCurrentBalance() - transacion.getAmount());
      break;
    case "EXPENSE":
      account.setCurrentBalance(account.getCurrentBalance() + transacion.getAmount());
      break;
    case "PAYMENT":
      account.setCurrentBalance(account.getCurrentBalance() + transacion.getAmount());
      break;

    default:
      throw new InvalidInputException("Required information is missing. The transaction type does not support");
    }

    accountRepository.save(account);

    // Record the balance history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account, account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);
  }

  private boolean isOverflowBalance(double balance, double amount) {
    if (balance > 0) {
      return amount > (Double.MAX_VALUE - balance);
    }

    return true;
  }

  private void validateTransactionRequest(ITransactionRequestVO transactionRequest) {
    if (transactionRequest.getCard() == null || transactionRequest.getAmount() == null
        || transactionRequest.getExtendedInformation() == null) {
      throw new InvalidInputException("Required information is missing");
    }

    ExtendedInformationVO extendedInformation = transactionRequest.getExtendedInformation();
    if (extendedInformation.getAdditionalTerminalInfo() == null) {
      throw new InvalidInputException("Required information is missing. Missing terminal information");
    }

    // validate transaction detail
    try {
      new JSONObject(extendedInformation.getTransactionDetails());

    } catch (JSONException e) {
      throw new InvalidInputException("Transaction detail is not valid!");
    }
  }

  private Account identifyValidAccount(String cardNumber, TargetAccountVO targetAccountVO) {

    // check validate card
    Optional<Card> cardOptional = Optional.ofNullable(cardRepository.findByCardCode(cardNumber));
    if (!cardOptional.isPresent()) {
      throw new ValidationException("Card number is not valid or not exist.");
    } else if (!cardOptional.get().getStatus().equals(StatusEnum.ACTIVE.getValue())) {
      throw new ValidationException("Card is inactive.");
    }

    Card card = cardOptional.get();

    // get account
    String accountType;
    if (targetAccountVO == null || targetAccountVO.getType() == null
        || "".equalsIgnoreCase(targetAccountVO.getType().trim())) {
      accountType = AccountTypeEnum.DEFAULT.getValue();
    } else {
      accountType = targetAccountVO.getType().toUpperCase();
    }
    List<Account> accounts = accountRepository.findByCardIdAndAccountType(card.getCardNumber(), accountType);

    if (accounts.size() != 1) {
      throw new EcashException("Error when get account.");
    }

    Account account = accounts.get(0);
    if (!account.getStatus().equals(StatusEnum.ACTIVE.getValue())) {
      throw new ValidationException("Account is inactive.");
    }

    return account;
  }

  private MerchantTerminal identifyValidMerchantTerminal(String terminalId) {
    Optional<MerchantTerminal> merchantTerminal = Optional.ofNullable(merchantTerminalRepository.findOne(terminalId));
    if (!merchantTerminal.isPresent()) {
      throw new ValidationException("Terminal id is not valid or not exist.");
    }

    return merchantTerminal.get();
  }

}
