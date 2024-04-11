package com.ticketwar.ticketwar.customer.exception;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String msg) {
    super(msg);
  }
}
