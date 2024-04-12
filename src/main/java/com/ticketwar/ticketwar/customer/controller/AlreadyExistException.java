package com.ticketwar.ticketwar.customer.controller;

public class AlreadyExistException extends RuntimeException {

  public AlreadyExistException(String message) {
    super(message);
  }
}
