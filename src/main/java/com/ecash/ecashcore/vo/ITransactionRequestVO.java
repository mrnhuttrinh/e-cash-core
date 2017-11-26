package com.ecash.ecashcore.vo;

public interface ITransactionRequestVO {
  default CardInformationVO getCard() {
    return null;
  }

  void setCard(CardInformationVO card);

  default Double getAmount() {
    return null;
  }

  void setAmount(Double amount);

  default ExtendedInformationVO getExtendedInformation() {
    return null;
  }

  void setExtendedInformation(ExtendedInformationVO extendedInformation);

  default TargetAccountVO getTargetAccount() {
    return null;
  }
}
