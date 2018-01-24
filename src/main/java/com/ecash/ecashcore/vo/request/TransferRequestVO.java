package com.ecash.ecashcore.vo.request;

import com.ecash.ecashcore.vo.TargetVO;
import com.ecash.ecashcore.vo.TransferExtendedInformationVO;

public class TransferRequestVO {
  private TargetVO sourceVO;
  private TargetVO destinationVO;
  private Double amount;
  private TransferExtendedInformationVO extendedInformation;

  public TargetVO getSourceVO() {
    return sourceVO;
  }

  public void setSourceVO(TargetVO sourceVO) {
    this.sourceVO = sourceVO;
  }

  public TargetVO getDestinationVO() {
    return destinationVO;
  }

  public void setDestinationVO(TargetVO destinationVO) {
    this.destinationVO = destinationVO;
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
