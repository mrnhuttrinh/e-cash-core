package com.ecash.ecashcore.vo;

public class ReportVO
{
  private ReportContent customer;
  private ReportContent account;
  private ReportContent card;
  private ReportContent wallet;
  private ReportContent user;

  public ReportVO(ReportContent customer, ReportContent account, ReportContent card,
      ReportContent wallet, ReportContent user)
  {
    super();
    this.customer = customer;
    this.account = account;
    this.card = card;
    this.wallet = wallet;
    this.user = user;
  }

  public ReportVO()
  {
    super();
  }

  public ReportContent getCustomer()
  {
    return customer;
  }

  public void setCustomer(ReportContent customer)
  {
    this.customer = customer;
  }

  public ReportContent getAccount()
  {
    return account;
  }

  public void setAccount(ReportContent account)
  {
    this.account = account;
  }

  public ReportContent getCard()
  {
    return card;
  }

  public void setCard(ReportContent card)
  {
    this.card = card;
  }

  public ReportContent getWallet()
  {
    return wallet;
  }

  public void setWallet(ReportContent wallet)
  {
    this.wallet = wallet;
  }

  public ReportContent getUser()
  {
    return user;
  }

  public void setUser(ReportContent user)
  {
    this.user = user;
  }

}
