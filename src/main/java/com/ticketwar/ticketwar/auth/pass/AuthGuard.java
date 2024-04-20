package com.ticketwar.ticketwar.auth.pass;

import com.ticketwar.ticketwar.exception.CustomException;
import com.ticketwar.ticketwar.exception.ExceptionStatus;
import jakarta.servlet.http.HttpServletRequest;

public abstract class AuthGuard {
  private AuthStrategy authStrategy;

  /**
   * 상속받은 클래스는 반드시 AuthStrategy를 지정해야합니다.
   *
   * @param authStrategy
   */
  public AuthGuard(AuthStrategy authStrategy) {
    this.authStrategy = authStrategy;
  }

  public final void check(HttpServletRequest request, String... args) {
    try {
      authStrategy.check(request, args);
    } catch (CustomException customException) {
      throw customException;
    } catch (RuntimeException e) {
      throw new CustomException(ExceptionStatus.UNAUTHORIZED_USER);
    }
  }
}
