package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.CardInformationVO;
import com.ecash.ecashcore.vo.ExtendedInformationVO;
import com.ecash.ecashcore.vo.TargetVO;

public class ChargeRequestVO implements IEcashTransactionRequestVO {
  private CardInformationVO card;
  private Double amount;
  private ExtendedInformationVO extendedInformation;

  public CardInformationVO getCard() {
    return card;
  }

  public void setCard(CardInformationVO card) {
    this.card = card;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public ExtendedInformationVO getExtendedInformation() {
    return extendedInformation;
  }

  public void setExtendedInformation(ExtendedInformationVO extendedInformation) {
    this.extendedInformation = extendedInformation;
  }

  @Override
  public TargetVO getTarget() {
    if (this.getExtendedInformation() != null) {
      return this.getExtendedInformation().getTargetAccount();
    }
    return null;
  }
}
