package com.ecash.ecashcore.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.AccountHolderTypeEnum;
import com.ecash.ecashcore.enums.CMSWalletStatusEnum;
import com.ecash.ecashcore.enums.CMSWalletTypeEnum;
import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.enums.TransactionTypeEnum;
import com.ecash.ecashcore.exception.DataNotFoundException;
import com.ecash.ecashcore.exception.EcashException;
import com.ecash.ecashcore.exception.InvalidInputException;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.AccountHolder;
import com.ecash.ecashcore.model.cms.BalanceHistory;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.MerchantStatement;
import com.ecash.ecashcore.model.cms.MerchantTerminal;
import com.ecash.ecashcore.model.cms.Transaction;
import com.ecash.ecashcore.model.cms.TransactionDetail;
import com.ecash.ecashcore.model.cms.TransactionType;
import com.ecash.ecashcore.model.cms.User;
import com.ecash.ecashcore.model.cms.Wallet;
import com.ecash.ecashcore.model.wallet.EWallet;
import com.ecash.ecashcore.model.wallet.EWalletTransaction;
import com.ecash.ecashcore.pojo.TransactionAccountDetailPOJO;
import com.ecash.ecashcore.pojo.UserTransactionPOJO;
import com.ecash.ecashcore.repository.AccountHolderRepository;
import com.ecash.ecashcore.repository.AccountRepository;
import com.ecash.ecashcore.repository.BalanceHistoryRepository;
import com.ecash.ecashcore.repository.CardRepository;
import com.ecash.ecashcore.repository.CustomerRepository;
import com.ecash.ecashcore.repository.EWalletRepository;
import com.ecash.ecashcore.repository.MerchantStatementRepository;
import com.ecash.ecashcore.repository.MerchantTerminalRepository;
import com.ecash.ecashcore.repository.TransactionDetailRepository;
import com.ecash.ecashcore.repository.TransactionRepository;
import com.ecash.ecashcore.repository.TransactionTypeRepository;
import com.ecash.ecashcore.repository.WalletRepository;
import com.ecash.ecashcore.util.StringUtils;
import com.ecash.ecashcore.vo.CustomerTransactionVO;
import com.ecash.ecashcore.vo.EcashExtendedInformationVO;
import com.ecash.ecashcore.vo.TargetVO;
import com.ecash.ecashcore.vo.TransactionVO;
import com.ecash.ecashcore.vo.ExtendedInformationVO;
import com.ecash.ecashcore.vo.request.ChargeRequestVO;
import com.ecash.ecashcore.vo.request.DepositRequestVO;
import com.ecash.ecashcore.vo.request.IEcashTransactionRequestVO;
import com.ecash.ecashcore.vo.request.RefundRequestVO;
import com.ecash.ecashcore.vo.request.TransferRequestVO;
import com.ecash.ecashcore.vo.request.WithdrawRequestVO;
import com.ecash.ecashcore.vo.response.TransactionResponseVO;
import com.querydsl.core.types.Predicate;

import javassist.NotFoundException;

@Service
@Transactional
public class TransactionService
{

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
  
  @Autowired
  EWalletRepository eWalletRepository;

  @Autowired
  UserService userService;

  @Autowired
  AccountHolderRepository accountHolderRepository;

  @Autowired
  MerchantStatementRepository merchantStatementRepository;
  
  static List<String> validTargetType  = Arrays.asList(new String[] { TargetVO.ACCOUNT, TargetVO.WALLET });

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public synchronized TransactionResponseVO transfer(TransferRequestVO request, String terminalId)
  {
    // Validate require information
    if (validateTransferRequest(request))
    {
      // Identify the merchant terminal
      MerchantTerminal merchantTerminal = merchantTerminalService
          .identifyValidMerchantTerminal(terminalId);
      
      if (request.getSource().getType().equalsIgnoreCase(TargetVO.ACCOUNT))
      {
        // Get source account
        Account sourceAccount = identifyValidAccount(request.getSource());
        
        if (request.getDestination().getType().equalsIgnoreCase(TargetVO.ACCOUNT))
        {
          // Transfer from account to account
          
          // Get destination account
          Account destinationAccount = identifyValidAccount(request.getDestination());
          
          return transferFromAccountToAccount(sourceAccount, destinationAccount, request, merchantTerminal);
        }
        else if(request.getDestination().getType().equalsIgnoreCase(TargetVO.WALLET)) {
          // Transfer from account to wallet
          
          // Get destination wallet
          String destinationWalletID = request.getDestination().getId();
          Wallet destinationWallet = walletRepository.findOne(destinationWalletID);
          if(destinationWallet == null) {
            throw new DataNotFoundException("Not found destination wallet.");
          }
          
          return transferFromAccountToWallet(sourceAccount, destinationWallet, request, merchantTerminal);
        }
        else
        {
          return null;
        }
      }
      else
      {
        return null;
      }
    }
    else
    {
      return null;
    }
  };

  private TransactionResponseVO transferFromAccountToAccount(Account source, Account destination,
      TransferRequestVO request, MerchantTerminal merchantTerminal)
  {
    Date transactionTime = Calendar.getInstance().getTime();
    // save history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, source,
        source.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    // calculate
    double remainAmount = source.getCurrentBalance() - request.getAmount();
    if (remainAmount < 0)
    {
      throw new ValidationException("Source account don't have enough money.");
    }

    // Update balance value
    source.setCurrentBalance(remainAmount);
    source = accountRepository.save(source);

    // save transaction in cms
    TransactionType transactionType = transactionTypeRepository
        .findOne(TransactionTypeEnum.TRANSFER.getName());
    Transaction transaction = Transaction.activeOf(source, transactionType, transactionTime,
        request.getAmount());
    transactionRepository.save(transaction);

    ExtendedInformationVO extendedInformation = request.getExtendedInformation();
    // save transaction detail
    TransactionDetail transactionDetail = TransactionDetail.activeOf(transaction,
        extendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(transactionDetail);

    // save history
    BalanceHistory destinationBalanceHistory = new BalanceHistory(transactionTime, destination,
        destination.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    // calculate
    double newAmount = destination.getCurrentBalance() + request.getAmount();

    // Update balance value
    destination.setCurrentBalance(newAmount);
    destination = accountRepository.save(destination);

    // save transaction in cms
    TransactionType destinationTransactionType = transactionTypeRepository
        .findOne(TransactionTypeEnum.DEPOSIT.getName());
    Transaction destinationTransaction = Transaction.activeOf(destination, destinationTransactionType, transactionTime,
        request.getAmount());
    destinationTransaction.setRelatedTransaction(transaction);
    transactionRepository.save(destinationTransaction);

    ExtendedInformationVO destinationExtendedInformation = request
        .getExtendedInformation();

    // save transaction detail
    TransactionDetail destinationTransactionDetail = TransactionDetail.activeOf(transaction,
        destinationExtendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(destinationTransactionDetail);

    return new TransactionResponseVO(transaction.getId(), transaction.getAccount().getId(),
        transactionTime);
  }

  private TransactionResponseVO transferFromAccountToWallet(Account source, Wallet destination,
      TransferRequestVO request, MerchantTerminal merchantTerminal)
  {
    // Find ewallet
    EWallet eWallet = eWalletRepository.findOne(destination.getRefId());
    if(eWallet == null) {
      throw new DataNotFoundException("Not found destination e-wallet.");
    }
    
    Date transactionTime = Calendar.getInstance().getTime();
    // save history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, source,
        source.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    // calculate
    double remainAmount = source.getCurrentBalance() - request.getAmount();
    if (remainAmount < 0)
    {
      throw new ValidationException("Source account don't have enough money.");
    }

    // Update balance value
    source.setCurrentBalance(remainAmount);
    source = accountRepository.save(source);

    // save transaction in cms
    TransactionType transactionType = transactionTypeRepository
        .findOne(TransactionTypeEnum.TRANSFER_OUT.getName());
    Transaction transaction = Transaction.activeOf(source, transactionType, transactionTime,
        request.getAmount());

    transactionRepository.save(transaction);

    ExtendedInformationVO extendedInformation = request.getExtendedInformation();

    // save transaction detail
    TransactionDetail transactionDetail = TransactionDetail.activeOf(transaction,
        extendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(transactionDetail);

    // save history (TODO: implement save history)
//    BalanceHistory destinationBalanceHistory = new BalanceHistory(transactionTime, destination,
//        destination.getCurrentBalance());
//    balanceHistoryRepository.save(balanceHistory);

    // calculate
    double newAmount = eWallet.getCurrentBalance() + request.getAmount();

    // Update balance value
    eWallet.setCurrentBalance(newAmount);
    eWallet = eWalletRepository.save(eWallet);

    // save transaction in cms (TODO: implement transaction with e-wallet)
//    TransactionType destinationTransactionType = transactionTypeRepository
//        .findOne(TransactionTypeEnum.DEPOSIT.getName());
//    Transaction destinationTransaction = new Transaction(destination, destinationTransactionType,
//        transactionTime, transferVO.getAmount());
//    destinationTransaction.setRelatedTransaction(transaction);
//    transactionRepository.save(destinationTransaction);

//    ExtendedInformationVO destinationExtendedInformation = request
//        .getExtendedInformation();

    // save transaction detail (TODO: implement transaction detail)
//    TransactionDetail destinationTransactionDetail = new TransactionDetail(transaction,
//        destinationExtendedInformation.getTransactionDetails());
//    transactionDetailRepository.save(destinationTransactionDetail);

    return new TransactionResponseVO(transaction.getId(),
        transaction.getAccount().getId(),
        transactionTime);
  }
  
  @Transactional(isolation = Isolation.SERIALIZABLE)
  public synchronized TransactionResponseVO withdraw(WithdrawRequestVO request, String terminalId) {
    // Validate require information
    validateWithdrawRequest(request);
    
    // Identify the merchant terminal
    MerchantTerminal merchantTerminal = merchantTerminalService
        .identifyValidMerchantTerminal(terminalId);

    // Check valid account information
    Account account = identifyValidAccount(request.getSource());

    Date transactionTime = Calendar.getInstance().getTime();

    // Record the balance history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account,
        account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);
    
    // calculate
    double remainAmount = account.getCurrentBalance() - request.getAmount();
    if (remainAmount < 0)
    {
      throw new ValidationException("Source account don't have enough money.");
    }

    // Update balance value
    account.setCurrentBalance(remainAmount);
    accountRepository.save(account);

    // Record the transaction
    TransactionType transactionType = transactionTypeRepository
        .findOne(TransactionTypeEnum.WITHDRAW.getName());
    Transaction transaction = Transaction.activeOf(account, transactionType, transactionTime,
        request.getAmount());
    transactionRepository.save(transaction);

    ExtendedInformationVO extendedInformation = request
        .getExtendedInformation();
    // save transaction detail
    TransactionDetail transactionDetail = TransactionDetail.activeOf(transaction,
        extendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(transactionDetail);

    return new TransactionResponseVO(transaction.getId(),
        transaction.getAccount().getId(), transactionTime);
  }
  
  private Account identifyValidAccount(TargetVO target) {
    // Must check target before step into this function.
    return identifyValidAccount(target.getId());
  }

  private Account identifyValidAccount(String accountId) {
    // Must check id before step into this function.
    Account account = accountRepository.findOne(accountId);

    if (account == null)
    {
      throw new ValidationException("Account not found. Id: " + accountId);
    }
    
    if (!account.getStatus().equals(StatusEnum.ACTIVE.toString())) {
      throw new ValidationException("Account is inactive. Id:" + accountId);
    } else {
      return account;
    }
  }
  
  private void validateWithdrawRequest(WithdrawRequestVO request) {
    if (request.getSource() == null || request.getAmount() == null
        || request.getExtendedInformation() == null) {
      throw new InvalidInputException("Required information is missing");
    }

    validateNegativeAmount(request.getAmount());

    validateTarget(request.getSource());
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public synchronized TransactionResponseVO chargeRequest(ChargeRequestVO request)
  {
    Account account = null;
    Wallet wallet = null;

    // validate
    validateTransactionRequest(request);

    // check validate card
    Card card = cardService.identifyValidCardByCardCode(request.getCard().getNumber());

    Date transactionTime = Calendar.getInstance().getTime();

    account = identifyValidAccount(card, request.getTarget());
    if (request.getTarget() != null)
    {
      try
      {
        wallet = identifyValidWallet(card, request.getTarget());
      }
      catch (ValidationException ve)
      {
        // Process with Account
      }

    }

    if (wallet != null)
    {
      // Make transaction in wallet
      EWalletTransaction eWalletTransaction = eWalletService
          .createEWalletTransaction(wallet.getRefId(), request.getAmount());
    }
    else
    {
      // save history
      BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account,
          account.getCurrentBalance());
      balanceHistoryRepository.save(balanceHistory);

      // calculate
      double remainAmount = account.getCurrentBalance() - request.getAmount();
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
    Transaction transaction = Transaction.activeOf(account, transactionType, transactionTime,
        request.getAmount(), card);
    transactionRepository.save(transaction);

    EcashExtendedInformationVO extendedInformation = request.getExtendedInformation();
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

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public synchronized TransactionResponseVO depositRequest(DepositRequestVO request)
  {

    // Validate require information
    validateTransactionRequest(request);

    // check validate card
    Card card = cardService.identifyValidCardByCardCode(request.getCard().getNumber());

    // Check valid account information
    Account account = identifyValidAccount(card, request.getTarget());

    // Check the last balance value is valid
    if (isOverflowBalance(account.getCurrentBalance(), request.getAmount()))
    {
      throw new InvalidInputException(
          "Required information is missing. The disposite amount's too large");
    }

    Date transactionTime = Calendar.getInstance().getTime();

    // Record the balance history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account,
        account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    // Update balance value
    account.setCurrentBalance(account.getCurrentBalance() + request.getAmount());
    accountRepository.save(account);

    // Record the transaction
    TransactionType transactionType = transactionTypeRepository
        .findOne(TransactionTypeEnum.DEPOSIT.getName());

    Transaction transaction = Transaction.activeOf(account, transactionType, transactionTime,
        request.getAmount(),
        card);
    transactionRepository.save(transaction);

    // Identify the merchant terminal
    EcashExtendedInformationVO extendedInformation = request.getExtendedInformation();
    MerchantTerminal merchantTerminal = merchantTerminalService
        .identifyValidMerchantTerminal(
            extendedInformation.getAdditionalTerminalInfo().getTerminalId());

    // Record the transaction detail
    TransactionDetail transactionDetail = TransactionDetail.activeOf(transaction,
        extendedInformation.getTransactionDetails(), merchantTerminal.getMerchant());
    transactionDetailRepository.save(transactionDetail);

    return new TransactionResponseVO(transaction.getId(), account.getId(),
        transactionTime);
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public synchronized TransactionResponseVO refundRequest(RefundRequestVO request)
  {

    // Validate transactionId
    String transactionId = request.getTransactionId();
    if (StringUtils.isNullOrEmpty(transactionId))
    {
      throw new InvalidInputException(
          "Required information is missing. The transaction Id is null or empty");
    }

    final Transaction transaction = identifyValidTransaction(transactionId);

    List<Transaction> transactions = transactionRepository
        .findByRelatedTransactionId(transactionId);
    if (!transactions.isEmpty())
    {
      throw new ValidationException("Transaction had already been refunded.");
    }

    // Can not support refund transaction
    if (transaction.getTransactionType().getTypeCode().equals(TransactionTypeEnum.REFUND.getName()))
    {
      throw new InvalidInputException(
          "Required information is missing. The transaction type does not support");
    }

    Date transactionTime = Calendar.getInstance().getTime();

    // Check the origin transaction from request terminal

    // Refund account balance
    refundAccountBalance(transaction.getAccount(), transaction, transactionTime);

    // Record the transaction
    TransactionType transactionType = transactionTypeRepository.findOne(TransactionTypeEnum.REFUND.getName());
    final Transaction refundTransaction = Transaction.activeOf(transaction.getAccount(), transactionType,
        transactionTime, transaction.getAmount(), transaction.getCard());

    // Record relate transaction
    refundTransaction.setRelatedTransaction(transaction);
    transactionRepository.save(refundTransaction);

    // Record the transaction detail
    TransactionDetail transactionDetail = transaction.getTransactionDetail();
    if (transactionDetail == null)
    {
      throw new EcashException("Error when get transaction detail.");
    }

    final TransactionDetail refundTransactionDetail = new TransactionDetail(refundTransaction,
        "Terminal canceled",
        transactionDetail.getMerchant());
    transactionDetailRepository.save(refundTransactionDetail);

    return new TransactionResponseVO(refundTransaction.getId(),
        transaction.getAccount().getId(),
        transactionTime, transaction.getId());
  }

  private Transaction identifyValidTransaction(String transactionId)
  {
    // Get transaction from database
    final Optional<Transaction> transaction = Optional
        .ofNullable(transactionRepository.findOne(transactionId));
    if (!transaction.isPresent())
    {
      throw new DataNotFoundException(
          "Required information is missing. The transaction Id does not exist");
    }

    return transaction.get();
  }

  private void refundAccountBalance(final Account account, final Transaction transacion,
      final Date transactionTime)
  {

    // Record the balance history
    BalanceHistory balanceHistory = new BalanceHistory(transactionTime, account,
        account.getCurrentBalance());
    balanceHistoryRepository.save(balanceHistory);

    // Update account balance
    switch (transacion.getTransactionType().getTypeCode())
    {
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

  private void validateNegativeAmount(double amount)
  {
    if (amount <= 0)
    {
      throw new ValidationException("Amount must be greater than 0.");
    }
  }

  private boolean isOverflowBalance(double balance, double amount)
  {
    if (balance >= 0)
    {
      return amount > (Double.MAX_VALUE - balance);
    }

    return true;
  }

  private boolean validateTransferRequest(TransferRequestVO request) {
    if (request.getSource() == null || request.getDestination() == null
        || request.getAmount() == null || request.getExtendedInformation() == null) {
      throw new InvalidInputException("Required information is missing");
    }
    
    validateNegativeAmount(request.getAmount());

    validateTarget(request.getSource());
    validateTarget(request.getDestination());

    return true;
  }
  
  private void validateTarget(TargetVO target) {
    if (StringUtils.isNullOrEmpty(target.getId())) {
      throw new InvalidInputException("Id must not be null.");
    }

    if (!validTargetType.contains(target.getType().toUpperCase())) {
      throw new InvalidInputException("Invalid target type.");
    }
  }

  private void validateTransactionRequest(IEcashTransactionRequestVO request)
  {
    if (request.getCard() == null || request.getAmount() == null
        || request.getExtendedInformation() == null)
    {
      throw new InvalidInputException("Required information is missing");
    }

    validateNegativeAmount(request.getAmount());

    EcashExtendedInformationVO extendedInformation = request.getExtendedInformation();
    if (extendedInformation.getAdditionalTerminalInfo() == null)
    {
      throw new InvalidInputException(
          "Required information is missing. Missing terminal information");
    }

    // validate transaction detail
    if (extendedInformation.getTransactionDetails() == null)
    {
      throw new InvalidInputException(
          "Required information is missing. Missing transaction details");
    }

    try
    {
      new JSONObject(extendedInformation.getTransactionDetails());
    }
    catch (JSONException e)
    {
      throw new InvalidInputException("Transaction detail is not valid!");
    }
  }

  private Account identifyValidAccount(Card card, TargetVO target)
  {

    Account account = card.getAccount();

    if (account == null)
    {
      throw new ValidationException("Card is invalid because account is undefined.");
    }

    if (!account.getStatus().equals(StatusEnum.ACTIVE.toString()))
    {
      throw new ValidationException("Account is inactive.");
    }
    else
    {
      return account;
    }
  }

  private Wallet identifyValidWallet(Card card, TargetVO target)
  {
    // get account
    String walletType;
    if (target == null || target.getType() == null
        || "".equalsIgnoreCase(target.getType().trim()))
    {
      walletType = CMSWalletTypeEnum.DEFAULT.toString();
    }
    else
    {
      walletType = target.getType().toUpperCase();
    }

    Wallet sampleWallet = new Wallet(walletType, card, CMSWalletStatusEnum.ACTIVE.toString());
    List<Wallet> wallets = walletRepository.findAll(Example.of(sampleWallet));
    if (wallets.isEmpty())
    {
      throw new ValidationException(
          String.format("There is not active wallet with %s type", target.getType()));
    }
    else
    {
      return wallets.get(0);
    }
  }

  public Iterable<Transaction> findAll(Predicate predicate, Pageable pageable)
  {
    return transactionRepository.findAll(predicate, pageable);
  }

  public List<TransactionVO> findTransactionByDateBetween(Date fromDate, Date toDate)
  {
    List<TransactionVO> listTransactionVO = new ArrayList<>();
    List<Transaction> temp = transactionRepository.findByDateBetweenAndCardIsNotNull(fromDate,
        toDate);
    temp.stream().forEach(transaction -> {
      listTransactionVO.add(modelMapper.map(transaction, TransactionVO.class));
    });
    return listTransactionVO;
  }

  public List<TransactionVO> findMerchantTransactionByDateBetween(Date fromDate, Date toDate)
  {
    List<TransactionVO> listTransactionVO = new ArrayList<>();
    List<Transaction> temp = transactionRepository.findByDateBetween(fromDate,
        toDate);
    temp.stream().filter(tran -> tran.getTransactionDetail() != null).forEach(transaction -> {
      listTransactionVO.add(modelMapper.map(transaction, TransactionVO.class));
    });
    return listTransactionVO;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE)
  public synchronized TransactionResponseVO merchantSettlement(TransactionVO transactionVO)
  {
    List<AccountHolder> accHolder = accountHolderRepository
        .findByHolderIdAndHolderType(transactionVO.getCardNumber(),
            AccountHolderTypeEnum.MERCHANT.getName());
    MerchantStatement merchantStatement = merchantStatementRepository
        .findOne(transactionVO.getRelatedTransactionId());
    if (!accHolder.isEmpty() && merchantStatement != null)
    {
      Account acc = accHolder.get(0).getAccount();
      Transaction trans = new Transaction();
      trans.setAccount(acc);
      trans.setAmount(transactionVO.getAmount());
      trans.setDate(transactionVO.getDate());
      trans.setStatus(TransactionStatus.ACTIVE.name());
      transactionRepository.save(trans);
      acc.setCurrentBalance(acc.getCurrentBalance() + trans.getAmount());
      accountRepository.save(acc);
      merchantStatement.setSettlement(true);
      merchantStatementRepository.save(merchantStatement);
      return new TransactionResponseVO(trans.getId(),
          trans.getAccount().getId(),
          trans.getDate(), trans.getId());
    }
    return null;
  }
  
  public UserTransactionPOJO findTransactionByUser(String currentUser, Date fromDate, Date toDate)
  {
    UserTransactionPOJO userTransactionVO = new UserTransactionPOJO();
    List<CustomerTransactionVO> customerTransactions = new ArrayList<CustomerTransactionVO>();
    User user = userService.getByUsername(currentUser);
    List<Customer> customers = user.getCustomers();

    for (Customer customer : customers)
    {

      CustomerTransactionVO customerTransaction = modelMapper.map(customer,
          CustomerTransactionVO.class);
      for (Account account : customer.getAccounts())
      {
        List<TransactionVO> listTransactionVO = new ArrayList<>();
        List<Transaction> temp = transactionRepository.findByDateBetweenAndAccount(fromDate, toDate, account);
        temp.stream().filter(tran -> tran.getTransactionDetail() != null).forEach(transaction -> {
          listTransactionVO.add(modelMapper.map(transaction, TransactionVO.class));
        });
        customerTransaction.setTransactions(listTransactionVO);
      }
      customerTransactions.add(customerTransaction);
    }
    userTransactionVO.setCustomerTransactions(customerTransactions);
    return userTransactionVO;
  }
  
  public TransactionAccountDetailPOJO getTransactionAccountDetail(String id) throws Exception {
    TransactionAccountDetailPOJO detailResult = new TransactionAccountDetailPOJO();
    
    // get transaction
    Transaction transaction = transactionRepository.findOne(id);
    if (transaction == null) {
      throw new NotFoundException("Transaction not found");
    }
    detailResult.setTransaction(transaction);
    // get account
    detailResult.setAccount(transaction.getAccount());
    // get customer
    detailResult.setCustomer(transaction.getAccount().getCustomer());
    // get card
    detailResult.setCard(transaction.getCard());
    // get transaction detail
    detailResult.setTransactionDetail(transaction.getTransactionDetail());
    // get merchant
    detailResult.setMerchant(transaction.getTransactionDetail().getMerchant());
    return detailResult;
  }
}
