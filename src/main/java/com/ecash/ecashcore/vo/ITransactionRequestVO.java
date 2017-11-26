package com.ecash.ecashcore.vo;

public interface ITransactionRequestVO {
	CardInformationVO getCard();

	void setCard(CardInformationVO card);

	Double getAmount();

	void setAmount(Double amount);

	ExtendedInformationVO getExtendedInformation();

	void setExtendedInformation(ExtendedInformationVO extendedInformation);
}
