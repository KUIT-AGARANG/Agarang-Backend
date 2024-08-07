package com.kuit.agarang.domain.login.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

  private SecretKey secretKey;
  @Value("${secret.jwt-access-expired-in}")
  private Long ACCESS_EXPIRED_IN;
  @Value("${secret.jwt-refresh-expired-in}")
  private Long REFRESH_EXPIRED_IN;

  public JWTUtil(@Value("${secret.jwt-secret-key}") String secret) {
    secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
  }

  public Long getMemberId(String token) {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("memberId", Long.class);
  }

  public String getProviderId(String token) {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("providerId", String.class);
  }

  public String getRole(String token) {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
  }

  public String getCategory(String token) {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
  }

  public Boolean isExpired(String token) {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
  }

  public String createAccessToken(String providerId, String role, Long memberId) {

    return Jwts.builder()
        .claim("category", "Authorization")
        .claim("providerId", providerId)
        .claim("role", role)
        .claim("memberId", memberId)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRED_IN))
        .signWith(secretKey)
        .compact();
  }

  public String createRefreshToken(String providerId, String role, Long memberId) {

    return Jwts.builder()
        .claim("category", "refresh")
        .claim("providerId", providerId)
        .claim("role", role)
        .claim("memberId", memberId)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRED_IN))
        .signWith(secretKey)
        .compact();
  }
}
