package com.ticketwar.ticketwar.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExceptionStatus {
  // user
  // Not found
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
  // Bad Request
  DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임입니다."),
  DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
  UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "허가되지 않은 유저입니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다."),
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
