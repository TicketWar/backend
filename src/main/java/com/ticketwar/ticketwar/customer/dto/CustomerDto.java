package com.ticketwar.ticketwar.customer.dto;

import lombok.*;

@Getter
public class CustomerDto {
  private final @NonNull String nickname;
  private final @NonNull String email;

  @Builder
  protected CustomerDto(@NonNull String nickname, @NonNull String email) {
    this.nickname = nickname;
    this.email = email;
  }
}
