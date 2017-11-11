package com.ecash.ecashcore.vo;

import java.util.Map;

public class ExtendedInformationVO {
  private String typeOfGoods;
  private TerminalInformationVO additionalTerminalInfo;
  private String invoiceNumber;
  private Map<String, Object> invoiceDetails;

  public String getTypeOfGoods() {
    return typeOfGoods;
  }

  public void setTypeOfGoods(String typeOfGoods) {
    this.typeOfGoods = typeOfGoods;
  }

  public TerminalInformationVO getAdditionalTerminalInfo() {
    return additionalTerminalInfo;
  }

  public void setAdditionalTerminalInfo(TerminalInformationVO additionalTerminalInfo) {
    this.additionalTerminalInfo = additionalTerminalInfo;
  }

  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  public Map<String, Object> getInvoiceDetails() {
    return invoiceDetails;
  }

  public void setInvoiceDetails(Map<String, Object> invoiceDetails) {
    this.invoiceDetails = invoiceDetails;
  }
}
