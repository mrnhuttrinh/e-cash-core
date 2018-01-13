package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.CardInformationVO;
import com.ecash.ecashcore.vo.ExtendedInformationVO;
import com.ecash.ecashcore.vo.TargetVO;

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

  default TargetVO getTarget() {
    return null;
  }
}
