package com.kuit.agarang.domain.ai.model.dto.gpt;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kuit.agarang.domain.ai.model.enums.GPTContentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class GPTContent {
  private String type;
  private String text;
  private ImageUrlContent imageUrl;

  @Getter
  @AllArgsConstructor(access = AccessLevel.PROTECTED)
  public static class ImageUrlContent {
    private String url;
  }

  public static GPTContent createTextContent(String prompt) {
    return new GPTContent(GPTContentType.TEXT.toLowerString(), prompt, null);
  }

  public static GPTContent createImageContent(String imageUrl) {
    return new GPTContent(GPTContentType.IMAGE_URL.toLowerString(), null, new ImageUrlContent(imageUrl));
  }
}
