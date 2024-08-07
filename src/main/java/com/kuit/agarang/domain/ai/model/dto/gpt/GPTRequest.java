package com.kuit.agarang.domain.ai.model.dto.gpt;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GPTRequest extends AbstractGPTRequest {
  public GPTRequest(List<GPTMessage> messages) {
    super(messages);
  }

  @Override
  public void setRequiredJson(boolean required) {
    super.setRequiredJson(required);
  }
}
