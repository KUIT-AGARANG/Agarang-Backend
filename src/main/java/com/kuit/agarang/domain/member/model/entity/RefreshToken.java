package com.kuit.agarang.domain.member.model.entity;

import com.kuit.agarang.global.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String providerId;
  private String value;
  private String expiration;

  @Builder
  public RefreshToken(String providerId, String value, String expiration) {
    this.providerId = providerId;
    this.value = value;
    this.expiration = expiration;
  }

  public static RefreshToken of(String providerId, String value, String expiration) {
    return RefreshToken.builder()
        .providerId(providerId)
        .value(value)
        .expiration(expiration)
        .build();
  }
}
