package com.kuit.agarang.domain.ai.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypecastClientUtil {

  private final WebClientUtil webClientUtil;
  @Value("${typecast.baseUrl}")
  private String baseUrl;
  @Value("${typecast.apiKey}")
  private String apiKey;

  public <T, V> T post(String uri, V requestDto, Class<T> responseClass) {
    return webClientUtil.post(baseUrl + uri, apiKey, requestDto, responseClass);
  }

  public <T> T get(String url, Class<T> responseClass) {
    return webClientUtil.get(url, apiKey, responseClass);
  }
}
