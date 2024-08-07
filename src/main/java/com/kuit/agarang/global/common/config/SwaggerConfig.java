package com.kuit.agarang.global.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI springOpenApi() {
    return new OpenAPI()
      .info(new Info()
        .title("Agarang's API Docs")
        .description("생성형 AI기반의 가족이 함께하는 태교 서비스")
        .version("v0.0.1"));
  }
}
