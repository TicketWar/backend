package com.ticketwar.ticketwar.exception;

public class CustomException extends RuntimeException {
    final private ExceptionStatus exceptionStatus;

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
