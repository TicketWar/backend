package com.ticketwar.ticketwar.auth.domain.role;

import com.ticketwar.ticketwar.auth.domain.jwt.JwtConfigure;
import com.ticketwar.ticketwar.auth.domain.jwt.JwtData;
import com.ticketwar.ticketwar.auth.domain.jwt.JwtUtil;
import com.ticketwar.ticketwar.auth.pass.AuthStrategy;
import com.ticketwar.ticketwar.exception.CustomException;
import com.ticketwar.ticketwar.exception.ExceptionStatus;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleStrategy implements AuthStrategy {

  @Autowired
  private JwtConfigure jwtConfigure;
  @Autowired
  private JwtUtil jwtUtil;

  /**
   * @param request
   * @param permittedRoles
   */
  @Override
  public void check(HttpServletRequest request, String[] permittedRoles) {
    final JwtData jwtData = getJwtData(request);
    final String role = jwtData.getRole();
    if (isPermittedRole(role, permittedRoles) == false) {
      throw new CustomException(ExceptionStatus.UNAUTHORIZED_USER);
    }
  }

  private JwtData getJwtData(HttpServletRequest request) throws RuntimeException {
    final String dataName = jwtConfigure.getDataName();
    JwtData jwtData = (JwtData) request.getAttribute(dataName);

    if (jwtData == null) {
      jwtData = jwtUtil.verifyTokenFromRequestAuthorizationHeader(request);
      request.setAttribute(dataName, jwtData);
    }
    return jwtData;
  }


  private boolean isPermittedRole(String role, String[] permittedRoles) {
    for (String permitted : permittedRoles) {
      System.out.println(permitted);
      if (permitted.equals(role)) {
        return true;
      }
    }
    return false;
  }
}
