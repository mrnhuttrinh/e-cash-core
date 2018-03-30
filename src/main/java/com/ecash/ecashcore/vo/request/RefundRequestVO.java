package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.CardInformationVO;
import com.ecash.ecashcore.vo.EcashExtendedInformationVO;

public class RefundRequestVO implements IEcashTransactionRequestVO {

  private String transactionId;

  private EcashExtendedInformationVO extendedInformation;

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public EcashExtendedInformationVO getExtendedInformation() {
    return extendedInformation;
  }

  public void setExtendedInformation(EcashExtendedInformationVO extendedInformation) {
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
