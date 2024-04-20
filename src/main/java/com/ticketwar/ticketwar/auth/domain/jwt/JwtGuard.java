package com.ticketwar.ticketwar.auth.domain.jwt;

import com.ticketwar.ticketwar.auth.pass.AuthGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtGuard extends AuthGuard {
  public JwtGuard(@Autowired JwtStrategy strategy) {
    super(strategy);
  }
}
