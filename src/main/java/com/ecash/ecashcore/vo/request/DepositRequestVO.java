package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.CardInformationVO;
import com.ecash.ecashcore.vo.EcashExtendedInformationVO;
import com.ecash.ecashcore.vo.TargetVO;

public class DepositRequestVO implements IEcashTransactionRequestVO {
	private CardInformationVO card;
	private Double amount;
	private EcashExtendedInformationVO extendedInformation;

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

	public EcashExtendedInformationVO getExtendedInformation() {
		return extendedInformation;
	}

	public void setExtendedInformation(EcashExtendedInformationVO extendedInformation) {
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
