package com.kuit.agarang.domain.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CharacterSettingResponse {

  private Long characterId;
  private String name;
  private String description;
  private String imageUrl;
}
