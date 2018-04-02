package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.CardInformationVO;
import com.ecash.ecashcore.vo.EcashExtendedInformationVO;
import com.ecash.ecashcore.vo.TargetVO;

public interface IEcashTransactionRequestVO extends ITransactionRequestVO {
  default CardInformationVO getCard() {
    return null;
  }

  void setCard(CardInformationVO card);

  default Double getAmount() {
    return null;
  }

  void setAmount(Double amount);

  default EcashExtendedInformationVO getExtendedInformation() {
    return null;
  }

  void setExtendedInformation(EcashExtendedInformationVO extendedInformation);

  default TargetVO getTarget() {
    return null;
  }
}
