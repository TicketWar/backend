package com.ticketwar.ticketwar.auth.domain.role;

import com.ticketwar.ticketwar.auth.pass.AuthGuard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleGuard extends AuthGuard {
  public RoleGuard(@Autowired RoleStrategy strategy) {
    super(strategy);
  }
}