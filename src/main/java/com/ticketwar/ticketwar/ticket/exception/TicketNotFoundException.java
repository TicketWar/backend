package com.ticketwar.ticketwar.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TicketNotFoundException extends RuntimeException {
  public TicketNotFoundException(String msg) {
    super(msg);
  }
}
