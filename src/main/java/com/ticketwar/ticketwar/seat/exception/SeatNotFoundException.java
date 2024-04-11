package com.ticketwar.ticketwar.seat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class SeatNotFoundException extends RuntimeException {
  public SeatNotFoundException(String msg) {
    super(msg);
  }
}
