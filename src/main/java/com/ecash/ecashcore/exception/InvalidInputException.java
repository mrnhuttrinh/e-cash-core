package com.ecash.ecashcore.exception;

public class InvalidInputException extends EcashException {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -1576851635242371333L;

  public InvalidInputException() {
    super();
  }

  public InvalidInputException(String message) {
    super(message);
  }
}
