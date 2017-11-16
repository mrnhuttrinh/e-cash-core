package com.ecash.ecashcore.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.enums.StatusEnum;
import com.ecash.ecashcore.enums.TransactionTypeEnum;
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
import com.ecash.ecashcore.util.JsonUtil;
import com.ecash.ecashcore.vo.ChargeRequestVO;
import com.ecash.ecashcore.vo.DepositRequestVO;
import com.ecash.ecashcore.vo.ExtendedInformationVO;
import com.ecash.ecashcore.vo.ITransactionRequestVO;

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

		validateTrasactionRequest(chargeRequest);

		Account account = identifyValidAccount(chargeRequest.getCard().getNumber());

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

		TransactionDetail transactionDetail = new TransactionDetail(transaction, extendedInformation.getTypeOfGoods(),
				JsonUtil.objectToJsonString(extendedInformation.getTransactionDetails()),
				merchantTerminal.getMerchant());
		transactionDetailRepository.save(transactionDetail);
		return transaction;
	}

	public Transaction depositRequest(DepositRequestVO depositRequest) {

		// Validate require information
		validateTrasactionRequest(depositRequest);

		// Check valid card information
		Account account = identifyValidAccount(depositRequest.getCard().getNumber());

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
		Transaction transaction = new Transaction(account, transactionType, transactionTime,
				depositRequest.getAmount());
		transactionRepository.save(transaction);

		// Identify the merchant terminal
		ExtendedInformationVO extendedInformation = depositRequest.getExtendedInformation();
		MerchantTerminal merchantTerminal = identifyValidMerchantTerminal(
				extendedInformation.getAdditionalTerminalInfo().getTerminalId());

		// Record the transaction detail
		TransactionDetail transactionDetail = new TransactionDetail(transaction, extendedInformation.getTypeOfGoods(),
				JsonUtil.objectToJsonString(extendedInformation.getTransactionDetails()),
				merchantTerminal.getMerchant());
		transactionDetailRepository.save(transactionDetail);

		return transaction;
	}

	private boolean isOverflowBalance(double balance, double amount) {
		if (balance > 0) {
			return amount > (Double.MAX_VALUE - balance);
		}

		return true;
	}

	private void validateTrasactionRequest(ITransactionRequestVO transactionRequest) {
		if (transactionRequest.getCard() == null || transactionRequest.getAmount() == null
				|| transactionRequest.getExtendedInformation() == null) {
			throw new InvalidInputException("Required information is missing");
		}

		ExtendedInformationVO extendedInformation = transactionRequest.getExtendedInformation();
		if (extendedInformation.getAdditionalTerminalInfo() == null) {
			throw new InvalidInputException("Required information is missing. Missing terminal information");
		}
	}

	private Account identifyValidAccount(String cardNumber) {

		Optional<Card> card = Optional.ofNullable(cardRepository.findByCardCode(cardNumber));
		if (!card.isPresent()) {
			throw new ValidationException("Card number is not valid or not exist.");
		} else if (!card.get().getStatus().equals(StatusEnum.ACTIVE.getValue())) {
			throw new ValidationException("Card is inactive.");
		}

		Account account = card.get().getAccount();
		if (!account.getStatus().equals(StatusEnum.ACTIVE.getValue())) {
			throw new ValidationException("Account is inactive.");
		}

		return account;
	}

	private MerchantTerminal identifyValidMerchantTerminal(String terminalId) {
		Optional<MerchantTerminal> merchantTerminal = Optional
				.ofNullable(merchantTerminalRepository.findOne(terminalId));
		if (!merchantTerminal.isPresent()) {
			throw new ValidationException("Terminal id is not valid or not exist.");
		}

		return merchantTerminal.get();
	}

}
