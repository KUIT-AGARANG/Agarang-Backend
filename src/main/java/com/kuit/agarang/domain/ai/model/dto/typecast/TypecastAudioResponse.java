package com.kuit.agarang.domain.ai.model.dto.typecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TypecastAudioResponse {
  private Result result;

  @Getter
  @NoArgsConstructor
  public static class Result {
    @JsonProperty("audio_download_url")
    private String audioDownloadUrl;
  }
}
