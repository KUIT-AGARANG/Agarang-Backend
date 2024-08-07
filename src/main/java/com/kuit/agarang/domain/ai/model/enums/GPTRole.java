package com.kuit.agarang.domain.ai.model.enums;

import lombok.Getter;

@Getter
public enum GPTRole {
  SYSTEM, USER, ASSISTANT;

  public String toLowerString() {
    return this.toString().toLowerCase();
  }
}
