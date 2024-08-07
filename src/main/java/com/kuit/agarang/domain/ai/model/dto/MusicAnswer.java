package com.kuit.agarang.domain.ai.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class MusicAnswer {
  private String id;
  private MusicChoice musicChoice;

  @Getter
  @ToString
  @NoArgsConstructor
  public static class MusicChoice {
    private String instrument;
    private String genre;
    private String mood;
    private String tempo;
  }
}
