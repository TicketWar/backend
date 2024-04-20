package com.ticketwar.ticketwar.auth.domain.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigure {
  @Value("${jwt.data.name:USER_DATA}")
  private String dataName;

  @Value("${jwt.secret}")
  private String secret;

  public String getDataName() {
    return dataName;
  }

  public String getSecret() {
    return secret;
  }
}
