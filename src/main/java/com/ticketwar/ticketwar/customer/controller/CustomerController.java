package com.ticketwar.ticketwar.customer.controller;

import com.ticketwar.ticketwar.customer.dto.CustomerReqDto;
import com.ticketwar.ticketwar.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  // 이후 auth controller 쪽으로 빠져야함.
  @PostMapping("/signup")
  public boolean signUp(@RequestBody CustomerReqDto customerReqDto) throws BadRequestException {
    return customerService.signUp(customerReqDto);
  }

  @PatchMapping("/{id}")
  public boolean update(@PathVariable("id") Long id, @RequestBody CustomerReqDto customerReqDto)
      throws BadRequestException {
    return customerService.update(id, customerReqDto);
  }
}
