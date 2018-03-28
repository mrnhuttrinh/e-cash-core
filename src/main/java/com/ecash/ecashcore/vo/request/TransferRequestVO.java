package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.TargetVO;
import com.ecash.ecashcore.vo.TransferExtendedInformationVO;

public class TransferRequestVO implements ITransactionRequestVO {
  private TargetVO source;
  private TargetVO destination;
  private Double amount;
  private TransferExtendedInformationVO extendedInformation;

  public TargetVO getSourceVO() {
    return source;
  }

  public void setSourceVO(TargetVO sourceVO) {
    this.source = sourceVO;
  }

  public TargetVO getDestinationVO() {
    return destination;
  }

  public void setDestinationVO(TargetVO destinationVO) {
    this.destination = destinationVO;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public TransferExtendedInformationVO getExtendedInformation() {
    return extendedInformation;
  }

  public void setExtendedInformation(TransferExtendedInformationVO extendedInformation) {
    this.extendedInformation = extendedInformation;
  }
}
