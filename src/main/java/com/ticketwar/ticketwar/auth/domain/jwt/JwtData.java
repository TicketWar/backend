package com.ticketwar.ticketwar.auth.domain.jwt;

import lombok.Builder;
import lombok.NonNull;
import lombok.ToString;

@ToString
public class JwtData {

  private Long id;
  private String nickname;
  private String email;
  private String role;

  public JwtData() {
  }

  @Builder
  public JwtData(@NonNull Long id, @NonNull String nickname, @NonNull String email, @NonNull String role) {
    this.id = id;
    this.nickname = nickname;
    this.email = email;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public String getNickname() {
    return nickname;
  }

  public String getEmail() {
    return email;
  }

  public String getRole() {
    return role;
  }
}
