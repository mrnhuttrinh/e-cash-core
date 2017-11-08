package com.ecash.ecashcore.exception;

public class EcashException extends RuntimeException {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -8231183569165178581L;

  public EcashException() {
    super();
  }

  public EcashException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public EcashException(String message, Throwable cause) {
    super(message, cause);
  }

  public EcashException(String message) {
    super(message);
  }

  public EcashException(Throwable cause) {
    super(cause);
  }
}
