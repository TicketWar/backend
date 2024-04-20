package com.ticketwar.ticketwar.auth.pass;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class GuardAspect {

  @Autowired
  private GuardResolver guardResolver;

  @Before("@annotation(useGuards)")
  public void handleUseGuards(UseGuards useGuards) {
    final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes()).getRequest();
    final List<Guard> guards = Arrays.stream(useGuards.value()).toList();
    for (Guard guard : guards) {
      AuthGuard authGuard = guardResolver.getGuard(guard.value().getSimpleName());
      authGuard.check(request, guard.args());
    }
  }

  @Before("@annotation(guard)")
  public void handleGuard(Guard guard) {
    final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
        .currentRequestAttributes()).getRequest();
    final AuthGuard authGuard =
        guardResolver.getGuard(guard.value().getSimpleName());
    authGuard.check(request, guard.args());
  }
}
