package com.ecash.ecashcore.vo;

public class RefundRequestVO implements ITransactionRequestVO {

  private String transactionId;

  private ExtendedInformationVO extendedInformation;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public ExtendedInformationVO getExtendedInformation() {
    return extendedInformation;
  }

  public void setExtendedInformation(ExtendedInformationVO extendedInformation) {
    this.extendedInformation = extendedInformation;
  }

  @Override
  public void setCard(CardInformationVO card) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setAmount(Double amount) {
    // TODO Auto-generated method stub
    
  }
}
