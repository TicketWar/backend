package com.ticketwar.ticketwar.customer.controller;

import com.ticketwar.ticketwar.customer.dto.CustomerReqDto;
import com.ticketwar.ticketwar.customer.dto.CustomerResDto;
import com.ticketwar.ticketwar.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Customer 관련 정보에 대한 Rest Controller
 */
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

  @GetMapping("/{id}")
  public CustomerResDto getById(@PathVariable("id") Long id) throws NotFoundException {
    return customerService.getById(id);
  }

  @GetMapping("/nickname/{nickname}")
  public CustomerResDto getByNickname(@PathVariable("nickname") String nickname)
      throws NotFoundException {
    return customerService.getByNickname(nickname);
  }

  @GetMapping("/email/{email}")
  public CustomerResDto getByEmail(@PathVariable("email") String email)
      throws NotFoundException {
    return customerService.getByEmail(email);
  }
}
