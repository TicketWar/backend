package com.ticketwar.ticketwar.customer.controller;

import com.ticketwar.ticketwar.customer.dto.CustomerDto;
import com.ticketwar.ticketwar.customer.entity.Customer;
import com.ticketwar.ticketwar.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Profile("min_version")
@Controller
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(@Qualifier("min_CustomerService") CustomerService customerService) {
    this.customerService = customerService;
  }

  // create
  @RequestMapping(path = "/customer", method = RequestMethod.PUT)
  public void createCustomer(@RequestBody CustomerDto customerDto) {
    if (customerService.existsByEmail(customerDto.getEmail())) {
      throw new AlreadyExistException("Email already exists");
    }
    if (customerService.existsByNickname(customerDto.getNickname())) {
      throw new AlreadyExistException("Nickname already exists");
    }
    customerService.create(customerDto.getNickname(), customerDto.getEmail());
  }

  // read
  // session 검증 후 내 정보만 전달...?/
  @RequestMapping(path = "/customer/{id}", method = RequestMethod.GET)
  public @ResponseBody CustomerDto getCustomer(@PathVariable Long id) {
    Customer customer = customerService.findById(id)
                                       .orElseThrow(NotFoundException::new);
    return CustomerDto.builder()
                      .nickname(customer.getNickname())
                      .email(customer.getEmail())
                      .build();
  }

  // update
  @RequestMapping(path = "/customer/{id}", method = RequestMethod.POST)
  public void updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
    Customer customer = customerService.findById(id)
                                       .orElseThrow(NotFoundException::new);
    if (customerService.existsByEmail(customerDto.getEmail())) {
      throw new AlreadyExistException("Email already exists");
    }
    if (customerService.existsByNickname(customerDto.getNickname())) {
      throw new AlreadyExistException("Nickname already exists");
    }
    customerService.create(customerDto.getNickname(), customerDto.getEmail());
  }

  @ExceptionHandler(AlreadyExistException.class)
  public ResponseEntity<String> handle(AlreadyExistException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handle(NotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }
}
