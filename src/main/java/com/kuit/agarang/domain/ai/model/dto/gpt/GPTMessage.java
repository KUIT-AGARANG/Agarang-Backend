package com.kuit.agarang.domain.ai.model.dto.gpt;

import com.kuit.agarang.domain.ai.model.enums.GPTRole;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GPTMessage {
  private String role;
  private Object content;

  @Builder
  public GPTMessage(GPTRole role, Object content) {
    this.role = role.toLowerString();
    this.content = content;
  }
}
