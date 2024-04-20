package com.ticketwar.ticketwar.auth.domain.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private final String BEARER = "BEARER ";
  @Autowired
  private JwtConfigure jwtConfigure;

  /**
   * Verify Authorization header's bearer token.
   * <p>
   * if fails, it throws RuntimeException.
   *
   * @param request
   * @return
   */
  public JwtData verifyTokenFromRequestAuthorizationHeader(HttpServletRequest request) {
    final String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION).substring(BEARER.length());
    final Algorithm algorithm = Algorithm.HMAC256(jwtConfigure.getSecret());
    final JWTVerifier jwtVerifier = JWT.require(algorithm).build();
    final DecodedJWT decodedJWT = jwtVerifier.verify(bearerToken);

    return getJwtDataFromDecodedJwt(decodedJWT);
  }

  // 해당 부분에 대해서 조금 더 깔끔하게 처리를 할 수 있으면 좋겠음.
  private JwtData getJwtDataFromDecodedJwt(DecodedJWT decodedJWT) {
    final Claim id = decodedJWT.getClaim("id");
    final Claim email = decodedJWT.getClaim("email");
    final Claim nickname = decodedJWT.getClaim("nickname");
    final Claim role = decodedJWT.getClaim("role");

    return JwtData.builder()
        .id(id.asLong())
        .email(email.asString())
        .nickname(nickname.asString())
        .role(role.asString())
        .build();
  }
}
