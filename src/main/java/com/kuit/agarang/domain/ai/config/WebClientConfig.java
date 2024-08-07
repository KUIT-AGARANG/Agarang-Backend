package com.kuit.agarang.domain.ai.config;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.netty.http.client.HttpClient;


@Configuration
public class WebClientConfig {

  DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();

  HttpClient httpClient = HttpClient.create()
    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000); // 연결 시간 10초

  @Bean
  public WebClient webClient() {
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
    return WebClient.builder()
      .uriBuilderFactory(factory)
      .codecs(config -> config.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .build();
  }
}
