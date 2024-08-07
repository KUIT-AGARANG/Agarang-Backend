package com.kuit.agarang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AgarangApplication {

  public static void main(String[] args) {
    SpringApplication.run(AgarangApplication.class, args);
  }

}
