package com.kuit.agarang.domain.ai.model.dto.gpt;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GPTUsage {
  long promptTokens;
  long completionTokens;
  long totalTokens;
}
