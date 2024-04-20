package com.ticketwar.ticketwar.auth.pass;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthStrategy {

  /**
   * Check method는 해당 Auth 작업에 대한 전략을 수행합니다. 만약, 검증 과정에서 실패할 경우 Error를 throw 합니다. 해당 Error는
   * Exception Handler 에서 처리할 수 있습니다.
   *
   * @param request
   */
  public void check(HttpServletRequest request, String... args);
}
