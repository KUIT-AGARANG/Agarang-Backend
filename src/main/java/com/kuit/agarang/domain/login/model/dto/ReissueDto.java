package com.kuit.agarang.domain.login.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReissueDto {
  private String newAccessToken;
  private String newRefreshToken;
  private String providerId;
  private String role;
}
