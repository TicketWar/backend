package com.ticketwar.ticketwar.user.dto;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Customer Output DTO
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class UserResDto {

  private Long id;
  private String nickname;
  private String email;

  @Builder
  protected UserResDto(@NonNull Long id, @NonNull String nickname, @NonNull String email) {
    this.id = id;
    this.nickname = nickname;
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserResDto that = (UserResDto) o;
    return Objects.equals(nickname, that.nickname) && Objects.equals(email,
        that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nickname, email);
  }
}
