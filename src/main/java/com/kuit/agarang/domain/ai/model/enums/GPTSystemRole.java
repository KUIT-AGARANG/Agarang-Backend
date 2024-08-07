package com.kuit.agarang.domain.ai.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum GPTSystemRole {
  IMAGE_DESCRIBER("You're the one who describes the image. Emotionally describe the image."),
  COUNSELOR("너는 상담사야."),
  ASSISTANT("You are an assistant."),
  MUSIC_PROMPT_ENGINEER("너는 text to music prompt 엔지니어야."),
  MUSIC_TITLE_WRITER("너는 한국어 음악 노래 작명가야.")
  ;

  private String text;
}
