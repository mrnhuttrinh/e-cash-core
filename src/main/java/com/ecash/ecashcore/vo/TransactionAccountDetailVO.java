package com.ecash.ecashcore.vo;

import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.Merchant;
import com.ecash.ecashcore.model.cms.Transaction;
import com.ecash.ecashcore.model.cms.TransactionDetail;

public class TransactionAccountDetailVO {
  private Card card;
  private Account account;
  private Customer customer;
  private Merchant merchant;
  private TransactionDetail transactionDetail;
  private Transaction transaction;
  public TransactionAccountDetailVO()
  {
    super();
  }
  public Card getCard() {
    return card;
  }
  public void setCard(Card card) {
    this.card = card;
  }
  public Account getAccount() {
    return account;
  }
  public void setAccount(Account account) {
    this.account = account;
  }
  public Customer getCustomer() {
    return customer;
  }
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
  public Merchant getMerchant() {
    return merchant;
  }
  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }
  public TransactionDetail getTransactionDetail() {
    return transactionDetail;
  }
  public void setTransactionDetail(TransactionDetail transactionDetail) {
    this.transactionDetail = transactionDetail;
  }
  public Transaction getTransaction() {
    return transaction;
  }
  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }
}
