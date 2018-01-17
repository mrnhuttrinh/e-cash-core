package com.ecash.ecashcore.service;

import com.ecash.ecashcore.enums.CMSWalletStatusEnum;
import com.ecash.ecashcore.enums.CMSWalletTypeEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.enums.TransactionTypeEnum;
import com.ecash.ecashcore.exception.DataNotFoundException;
import com.ecash.ecashcore.exception.EcashException;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.BalanceHistory;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.MerchantTerminal;
import com.ecash.ecashcore.model.cms.Transaction;
import com.ecash.ecashcore.model.cms.TransactionDetail;
import com.ecash.ecashcore.model.cms.TransactionType;
import com.ecash.ecashcore.model.cms.Wallet;
import com.ecash.ecashcore.model.wallet.EWalletTransaction;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.BalanceHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.ecash.ecashcore.repository.MerchantTerminalRepository;
import com.ecash.ecashcore.repository.TransactionDetailRepository;
import com.ecash.ecashcore.repository.TransactionRepository;
import com.ecash.ecashcore.repository.TransactionTypeRepository;
import com.ecash.ecashcore.repository.WalletRepository;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.ExtendedInformationVO;
import com.ecash.ecashcore.vo.TargetVO;
import com.ecash.ecashcore.vo.TransactionVO;
import com.ecash.ecashcore.vo.request.ChargeRequestVO;
import com.ecash.ecashcore.vo.request.DepositRequestVO;
import com.ecash.ecashcore.vo.request.ITransactionRequestVO;
import com.ecash.ecashcore.vo.request.RefundRequestVO;
import com.ecash.ecashcore.vo.response.TransactionResponseVO;
import com.querydsl.core.types.Predicate;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

  @Autowired
  CardRepository cardRepository;

  @Autowired
  CustomerRepository customerRepository;

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

  @Autowired
  CardService cardService;

  @Autowired
  WalletRepository walletRepository;

  @Autowired
  MerchantTerminalService merchantTerminalService;

  @Autowired
  ModelMapper modelMapper;

  @Autowired
  EWalletService eWalletService;

  public TransactionResponseVO chargeRequest(ChargeRequestVO chargeRequest) {
    Account account = null;
    Wallet wallet = null;

    // validate
    validateTransactionRequest(chargeRequest);

    // check validate card
    Card card = cardService.identifyValidCard(chargeRequest.getCard().getNumber());

    Date transactionTime = Calendar.getInstance().getTime();


    account = identifyValidAccount(card, chargeRequest.getTarget());
    wallet = identifyValidWallet(card, chargeRequest.getTarget());

    if (wallet != null) {
      // Make transaction in wallet
      EWalletTransaction eWalletTransaction =
          eWalletService.createEWalletTransaction(wallet.getRefId(), chargeRequest.getAmount());
    } else {
      // save history
      BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account,
          account.getCurrentBalance());
      balanceHistoryRepository.save(balanceHistory);

      // calculate
      double remainAmount = account.getCurrentBalance() - chargeRequest.getAmount();
      if (remainAmount < 0)
      {
        throw new ValidationException("Account don't have enough money.");
      }

      // Update balance value
      account.setCurrentBalance(remainAmount);
      account = accountRepository.save(account);
    }

    // save transaction in cms
    TransactionType transactionType = transactionTypeRepository
        .findOne(TransactionTypeEnum.EXPENSE.getName());
    Transaction transaction = new Transaction(account, transactionType, transactionTime,
        chargeRequest.getAmount(),
        card);
    transactionRepository.save(transaction);

    ExtendedInformationVO extendedInformation = chargeRequest.getExtendedInformation();

    // save transaction detail
    MerchantTerminal merchantTerminal = merchantTerminalService
        .identifyValidMerchantTerminal(
            extendedInformation.getAdditionalTerminalInfo().getTerminalId());
    TransactionDetail transactionDetail = new TransactionDetail(transaction,
        extendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(transactionDetail);

    return new TransactionResponseVO(transaction.getId(), account.getId(),
        transactionTime);
  }

  public TransactionResponseVO depositRequest(DepositRequestVO depositRequest) {

    // Validate require information
    validateTransactionRequest(depositRequest);

    // check validate card
    Card card = cardService.identifyValidCard(depositRequest.getCard().getNumber());

    // Check valid account information
    Account account = identifyValidAccount(card, depositRequest.getTarget());

    // Check valid number.
    if (depositRequest.getAmount() <= 0) {
      throw new InvalidInputException(
          "Required information is missing. Missing amount information");
    }

    // Check the last balance value is valid
    if (isOverflowBalance(account.getCurrentBalance(), depositRequest.getAmount())) {
      throw new InvalidInputException(
          "Required information is missing. The disposite amount's too large");
    }

    Date transactionTime = Calendar.getInstance().getTime();

    // Record the balance history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account,
        account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    // Update balance value
    account.setCurrentBalance(account.getCurrentBalance() + depositRequest.getAmount());
    accountRepository.save(account);

    // Record the transaction
    TransactionType transactionType = transactionTypeRepository
        .findOne(TransactionTypeEnum.DEPOSIT.getName());
    Transaction transaction = new Transaction(account, transactionType, transactionTime,
        depositRequest.getAmount(),
        card);
    transactionRepository.save(transaction);

    // Identify the merchant terminal
    ExtendedInformationVO extendedInformation = depositRequest.getExtendedInformation();
    MerchantTerminal merchantTerminal = merchantTerminalService
        .identifyValidMerchantTerminal(
            extendedInformation.getAdditionalTerminalInfo().getTerminalId());

    // Record the transaction detail
    TransactionDetail transactionDetail = new TransactionDetail(transaction,
        extendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(transactionDetail);

    return new TransactionResponseVO(transaction.getId(), account.getAccountName(),
        transactionTime);
  }

  public TransactionResponseVO refundRequest(RefundRequestVO refundRequest) {

    // Validate transactionId
    String transactionId = refundRequest.getTransactionId();
    if (StringUtils.isNullOrEmpty(transactionId)) {
      throw new InvalidInputException(
          "Required information is missing. The transaction Id is null or empty");
    }

    final Transaction transaction = identifyValidTransaction(transactionId);

    List<Transaction> transactions = transactionRepository
        .findByRelatedTransactionId(transactionId);
    if (!transactions.isEmpty()) {
      throw new ValidationException("Transaction had already been refunded.");
    }

    // Can not support refund transaction
    if (transaction.getTransactionType().getTypeCode().equals(TransactionTypeEnum.REFUND.getName())) {
      throw new InvalidInputException(
          "Required information is missing. The transaction type does not support");
    }

    Date transactionTime = Calendar.getInstance().getTime();

    // Check the origin transaction from request terminal

    // Refund account balance
    refundAccountBalance(transaction.getAccount(), transaction, transactionTime);

    // Record the transaction
    TransactionType transactionType = transactionTypeRepository
        .findOne(TransactionTypeEnum.REFUND.getName());
    final Transaction refundTransaction = new Transaction(transaction.getAccount(), transactionType,
        transactionTime,
        transaction.getAmount(), transaction.getCard());

    // Record relate transaction
    refundTransaction.setRelatedTransaction(transaction);
    transactionRepository.save(refundTransaction);

    // Record the transaction detail
    TransactionDetail transactionDetail = transaction.getTransactionDetail();
    if (transactionDetail == null) {
      throw new EcashException("Error when get transaction detail.");
    }

    final TransactionDetail refundTransactionDetail = new TransactionDetail(refundTransaction,
        "Terminal canceled",
        transactionDetail.getMerchant());
    transactionDetailRepository.save(refundTransactionDetail);

    return new TransactionResponseVO(refundTransaction.getId(),
        transaction.getAccount().getAccountName(),
        transactionTime, transaction.getId());
  }

  private Transaction identifyValidTransaction(String transactionId) {
    // Get transaction from database
    final Optional<Transaction> transaction = Optional
        .ofNullable(transactionRepository.findOne(transactionId));
    if (!transaction.isPresent()) {
      throw new DataNotFoundException(
          "Required information is missing. The transaction Id does not exist");
    }

    return transaction.get();
  }

  private void refundAccountBalance(final Account account, final Transaction transacion,
                                    final Date transactionTime) {

    // Record the balance history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account,
        account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    // Update account balance
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
        throw new InvalidInputException(
            "Required information is missing. The transaction type does not support");
    }

    accountRepository.save(account);
  }

  private void validateNegativeAmount(double amount) {
    if (amount <= 0) {
      throw new ValidationException("Amount must be greater than 0.");
    }
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

    validateNegativeAmount(transactionRequest.getAmount());

    ExtendedInformationVO extendedInformation = transactionRequest.getExtendedInformation();
    if (extendedInformation.getAdditionalTerminalInfo() == null) {
      throw new InvalidInputException(
          "Required information is missing. Missing terminal information");
    }

    // validate transaction detail
    if (extendedInformation.getTransactionDetails() == null) {
      throw new InvalidInputException(
          "Required information is missing. Missing transaction details");
    }

    try {
      new JSONObject(extendedInformation.getTransactionDetails());
    } catch (JSONException e) {
      throw new InvalidInputException("Transaction detail is not valid!");
    }
  }

  private Account identifyValidAccount(Card card, TargetVO target) {

    Account account = card.getAccount();

    if (account == null) {
      throw new ValidationException("Card is invalid because account is undefined.");
    }

    if (!account.getStatus().equals(StatusEnum.ACTIVE.toString())) {
      throw new ValidationException("Account is inactive.");
    } else {
      return account;
    }
  }

  private Wallet identifyValidWallet(Card card, TargetVO target) {
    // get account
    String walletType;
    if (target == null || target.getType() == null
        || "".equalsIgnoreCase(target.getType().trim())) {
      walletType = CMSWalletTypeEnum.DEFAULT.toString();
    } else {
      walletType = target.getType().toUpperCase();
    }

    Wallet sampleWallet = new Wallet(walletType, card, CMSWalletStatusEnum.ACTIVE.toString());
    List<Wallet> wallets = walletRepository.findAll(Example.of(sampleWallet));
    if (wallets.isEmpty()) {
      return null;
    } else {
      return wallets.get(0);
    }
  }

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
