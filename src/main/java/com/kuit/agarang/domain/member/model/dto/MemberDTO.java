package com.kuit.agarang.domain.member.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class MemberDTO {

  private Long memberId;
  private String providerId;
  private String name;
  private String role;

  public static MemberDTO of(String providerId, String name, String role) {
    return MemberDTO.builder()
        .providerId(providerId)
        .name(name)
        .role(role)
        .build();
  }
}
