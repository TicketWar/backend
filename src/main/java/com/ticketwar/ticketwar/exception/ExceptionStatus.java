package com.ticketwar.ticketwar.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExceptionStatus {
  DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다."),
  DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
  ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 주문입니다."),
  TICKET_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 티켓입니다."),
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
