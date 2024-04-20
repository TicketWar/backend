package com.ticketwar.ticketwar.auth.pass;

import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GuardResolver {

  private final Map<String, AuthGuard> guards;

  public GuardResolver(List<AuthGuard> guards) {
    this.guards = guards.stream()
        .collect(
            Collectors.toMap(
                g -> g.getClass().getSimpleName(),
                Function.identity()
            ));
  }

  public AuthGuard getGuard(@NonNull String guardName) {
    return this.guards.get(guardName);
  }
}
