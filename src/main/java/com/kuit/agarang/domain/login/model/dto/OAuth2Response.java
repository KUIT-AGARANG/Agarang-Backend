package com.kuit.agarang.domain.login.model.dto;

public interface OAuth2Response {

  String getProvider(); // 제공자
  String getProviderId(); // 제공자 부여 ID
  String getEmail();
  String getName();
}
