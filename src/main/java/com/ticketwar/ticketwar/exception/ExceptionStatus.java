package com.ticketwar.ticketwar.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExceptionStatus {
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
  ;

  private final int code;
  private final String msg;
  private final String err;

  ExceptionStatus(HttpStatus httpStatus, String msg) {
    this.code = httpStatus.value();
    this.msg = msg;
    this.err = httpStatus.getReasonPhrase();
  }

  public int getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public String getErr() {
    return err;
  }
}
