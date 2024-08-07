package com.kuit.agarang.domain.ai.model.dto.typecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TypecastRequest {

  @JsonProperty("actor_id")
  private String actorId;
  private String text;
  private String lang;
  private Double tempo;
  private Integer volume;
  private Integer pitch;
  @JsonProperty("xapi_hd")
  private Boolean xapiHd;
  @JsonProperty("max_seconds")
  private Integer maxSeconds;
  @JsonProperty("model_version")
  private String modelVersion;
  @JsonProperty("xapi_audio_format")
  private String xapiAudioFormat;

  @Builder
  public TypecastRequest(String actorId, String text, String lang, Double tempo, Integer volume, Integer pitch, Boolean xapiHd, Integer maxSeconds, String modelVersion, String xapiAudioFormat) {
    this.actorId = actorId;
    this.text = text;
    this.lang = lang;
    this.tempo = tempo;
    this.volume = volume;
    this.pitch = pitch;
    this.xapiHd = xapiHd;
    this.maxSeconds = maxSeconds;
    this.modelVersion = modelVersion;
    this.xapiAudioFormat = xapiAudioFormat;
  }

  public static TypecastRequest create(String text, String actorId) {
    return TypecastRequest.builder()
      .actorId(actorId)
      .text(text)
      .lang("auto")
      .tempo(1.0)
      .volume(100)
      .pitch(0)
      .xapiHd(true)
      .maxSeconds(60)
      .modelVersion("latest")
      .xapiAudioFormat("wav")
      .build();
  }
}
