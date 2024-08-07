package com.kuit.agarang.domain.ai.model.dto.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GPTChat {
  private GPTRequest gptRequest;
  private GPTResponse gptResponse;
}
