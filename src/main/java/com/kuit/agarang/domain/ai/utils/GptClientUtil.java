package com.kuit.agarang.domain.ai.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GptClientUtil {

  private final WebClientUtil webClientUtil;
  @Value("${chat-gpt.baseUrl}")
  private String baseUrl;
  @Value("${chat-gpt.apiKey}")
  private String apiKey;

  public <T, V> T post(V requestDto, Class<T> responseClass) {
    return webClientUtil.post(baseUrl, apiKey, requestDto, responseClass);
  }
}
