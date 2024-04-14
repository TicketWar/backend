package com.ticketwar.ticketwar.customer.dto;

import com.ticketwar.ticketwar.common.interfaces.EntityConvertable;
import com.ticketwar.ticketwar.customer.entity.Customer;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Customer Request 에서 발생하는 DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CustomerReqDto implements EntityConvertable<Customer> {

  private String nickname;
  private String email;

  @Builder
  protected CustomerReqDto(@NonNull String nickname, @NonNull String email) {
    this.nickname = nickname;
    this.email = email;
  }

  @Override
  public Customer toEntity() {
    return Customer.builder()
                   .nickname(getNickname())
                   .email(getEmail())
                   .build();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerReqDto that = (CustomerReqDto) o;
    return Objects.equals(nickname, that.nickname) && Objects.equals(email,
        that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nickname, email);
  }
}
