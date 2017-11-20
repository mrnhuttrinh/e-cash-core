package com.ecash.ecashcore.vo;

public class ChargeRequestVO implements ITransactionRequestVO {
	private CardInformationVO card;
	private Double amount;
	private ExtendedInformationVO extendedInformation;
	private TargetAccountVO targetAccount;

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

	public TargetAccountVO getTargetAccount() {
		return targetAccount;
	}

	public void setTargetAccount(TargetAccountVO targetAccount) {
		this.targetAccount = targetAccount;
	}
}
