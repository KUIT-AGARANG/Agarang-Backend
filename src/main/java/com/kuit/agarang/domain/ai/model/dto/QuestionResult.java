package com.kuit.agarang.domain.ai.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionResult {
  private String id;
  private String text;
  private String audioUrl;

  @Builder
  public QuestionResult(String id, String text, String audioUrl) {
    this.id = id;
    this.text = text;
    this.audioUrl = audioUrl;
  }
}
