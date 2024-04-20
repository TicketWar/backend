package com.ticketwar.ticketwar.auth.domain.jwt;

import com.ticketwar.ticketwar.auth.pass.AuthStrategy;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtStrategy implements AuthStrategy {
  @Autowired
  private JwtConfigure jwtConfigure;
  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public void check(HttpServletRequest request, String... args) {
    final JwtData jwtData = jwtUtil.verifyTokenFromRequestAuthorizationHeader(request);
    request.setAttribute(jwtConfigure.getDataName(), jwtData);
  }
  
}
