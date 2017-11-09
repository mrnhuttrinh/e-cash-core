package com.ecash.ecashcore.vo;

import java.util.Date;

public class PaymentVO {
  private String terminalId;
  private Double amount;
  private String terminalSign;
  private String cardCode;
  private Date time;
  private String detail;

  public String getTerminalId() {
    return terminalId;
  }

  public void setTerminalId(String terminalId) {
    this.terminalId = terminalId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getTerminalSign() {
    return terminalSign;
  }

  public void setTerminalSign(String terminalSign) {
    this.terminalSign = terminalSign;
  }

  public String getCardCode() {
    return cardCode;
  }

  public void setCardCode(String cardCode) {
    this.cardCode = cardCode;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }
}
