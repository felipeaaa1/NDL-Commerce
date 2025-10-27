package com.ndlcommerce.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ndlcommerce.adapters.persistence.UserDataMapper;
import com.ndlcommerce.useCase.exception.BusinessException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  public String generateToken(UserDataMapper user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      String token =
          JWT.create()
              .withIssuer("auth-api")
              .withSubject(user.getName())
              .withExpiresAt(genExpirationDate())
              .sign(algorithm);
      return token;
    } catch (JWTCreationException exception) {
      System.err.println(exception.toString());
      throw new BusinessException("Erro ao gerar token");
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm).withIssuer("auth-api").build().verify(token).getSubject();
    } catch (JWTVerificationException exception) {
      System.err.println(exception.toString());
      throw new BusinessException("Erro ao gerar token");
    }
  }

  private Instant genExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
