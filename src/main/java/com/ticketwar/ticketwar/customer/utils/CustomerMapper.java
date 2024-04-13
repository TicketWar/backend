package com.ticketwar.ticketwar.customer.utils;

import com.ticketwar.ticketwar.customer.dto.CustomerResDto;
import com.ticketwar.ticketwar.customer.entity.Customer;

/**
 * Customer Entity를 DTO 로 변환하는 유틸리티 클래스
 */
public class CustomerMapper {

  public CustomerResDto mapToResDto(Customer c) {
    return CustomerResDto.builder()
                         .id(c.getId())
                         .nickname(c.getNickname())
                         .email(c.getEmail())
                         .build();
  }
}
