package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.TargetVO;
import com.ecash.ecashcore.vo.ExtendedInformationVO;

public class WithdrawRequestVO {
  private TargetVO source;
  private Double amount;
  private String accountHolder;
  private String content;
  private String teller;
  private ExtendedInformationVO extendedInformation;
  
  public WithdrawRequestVO() {
    super();
  }
  
  public TargetVO getSource() {
    return source;
  }
  public void setSource(TargetVO source) {
    this.source = source;
  }
  public Double getAmount() {
    return amount;
  }
  public void setAmount(Double amount) {
    this.amount = amount;
  }
  public String getAccountHolder() {
    return accountHolder;
  }
  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getTeller() {
    return teller;
  }
  public void setTeller(String teller) {
    this.teller = teller;
  }
  public ExtendedInformationVO getExtendedInformation() {
    return extendedInformation;
  }
  public void setExtendedInformation(ExtendedInformationVO extendedInformation) {
    this.extendedInformation = extendedInformation;
  }
}
