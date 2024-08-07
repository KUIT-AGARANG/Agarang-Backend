package com.kuit.agarang.domain.ai.utils;

import com.kuit.agarang.domain.ai.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebClientUtil {

  private final WebClientConfig webClientConfig;
  private static final String AUTHORIZATION_KEY = "Authorization";
  private static final String AUTHORIZATION_PREFIX = "Bearer ";

  public <T, V> T post(String url, String apiKey, V requestDto, Class<T> responseClass) {
    return webClientConfig.webClient().post()
      .uri(url)
      // TODO : gpt, metamusic 연동 구현해보고 각각의 유틸로 빼는 것 고려하기
      .header(AUTHORIZATION_KEY, AUTHORIZATION_PREFIX + apiKey)
      .bodyValue(requestDto)
      .retrieve()
      // TODO : Exception 처리 추가 후 수정하기
      .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class).map(Exception::new))
      .bodyToMono(responseClass)
      .block();
  }

  public <T> T get(String url, String apiKey, Class<T> responseClass) {
    return webClientConfig.webClient().get()
      .uri(url)
      // TODO : gpt, metamusic 연동 구현해보고 각각의 유틸로 빼는 것 고려하기
      .header(AUTHORIZATION_KEY, AUTHORIZATION_PREFIX + apiKey)
      .retrieve()
      // TODO : Exception 처리 추가 후 수정하기
      .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(String.class).map(Exception::new))
      .bodyToMono(responseClass)
      .block();
  }
}
