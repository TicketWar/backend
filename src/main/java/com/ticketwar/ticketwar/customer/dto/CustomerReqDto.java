package com.ticketwar.ticketwar.customer.dto;

import com.ticketwar.ticketwar.customer.entity.Customer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerReqDto {

  private String nickname;
  private String email;

  @Builder
  protected CustomerReqDto(@NonNull String nickname, @NonNull String email) {
    this.nickname = nickname;
    this.email = email;
  }

  public Customer toCustomer() {
    return Customer.builder()
                   .nickname(getNickname())
                   .email(getEmail())
                   .build();
  }
}
