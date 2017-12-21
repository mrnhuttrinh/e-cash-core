package com.ecash.ecashcore.enums;

public enum IdentifyDocumentTypeEnum {
  IDENTIFY_CARD("IDENTIFY_CARD"),
  PASSPORT_CARD("PASSPORT_CARD");

  String name;

  private IdentifyDocumentTypeEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
