package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.CardInformationVO;

public class UpdateCardStatusRequestVO {

  private CardInformationVO card;
  private String status;

  public UpdateCardStatusRequestVO() {
    super();
  }

  public UpdateCardStatusRequestVO(CardInformationVO cardInformation, String status) {
    super();
    this.card = cardInformation;
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CardInformationVO getCard() {
    return card;
  }

  public void setCard(CardInformationVO card) {
    this.card = card;
  }
}
