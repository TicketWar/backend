package com.ticketwar.ticketwar.exception;

public class CustomException extends RuntimeException {
  private final ExceptionStatus exceptionStatus;

  public CustomException(ExceptionStatus exceptionStatus) {
    this.exceptionStatus = exceptionStatus;
  }

  public ExceptionStatus getExceptionStatus() {
    return exceptionStatus;
  }

  @Override
  public String getMessage() {
    return exceptionStatus.getMsg();
  }
}
