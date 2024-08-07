package com.kuit.agarang.domain.ai.model.dto.typecast;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TypecastWebhookResponse {
  private String status;
  private String audioDownloadUrl;
  private String speakUrl;
}