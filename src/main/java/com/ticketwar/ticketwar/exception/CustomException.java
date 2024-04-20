package com.ticketwar.ticketwar.exception;

import java.util.function.Supplier;

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

  public static Supplier<CustomException> build(ExceptionStatus exceptionStatus) {
    return () -> new CustomException(exceptionStatus);
  }
}
