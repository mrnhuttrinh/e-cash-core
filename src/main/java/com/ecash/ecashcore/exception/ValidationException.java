package com.ecash.ecashcore.exception;

public class ValidationException extends EcashException{
  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -3455123109329077012L;

  public ValidationException() {
    super();
  }

  public ValidationException(String message) {
    super(message);
  }
}
