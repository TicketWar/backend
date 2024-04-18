package com.ticketwar.ticketwar.user.dto;

import com.ticketwar.ticketwar.common.interfaces.EntityConvertable;
import com.ticketwar.ticketwar.user.entity.User;
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
public class UserReqDto implements EntityConvertable<User> {

  private String nickname;
  private String email;

  @Builder
  protected UserReqDto(@NonNull String nickname, @NonNull String email) {
    this.nickname = nickname;
    this.email = email;
  }

  @Override
  public User toEntity() {
    return User.builder()
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
    UserReqDto that = (UserReqDto) o;
    return Objects.equals(nickname, that.nickname) && Objects.equals(email,
        that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nickname, email);
  }
}
