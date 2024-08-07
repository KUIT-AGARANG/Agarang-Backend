package com.kuit.agarang.global.common.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @Value("${deploy.env}")
  private String env;

  @GetMapping("/env")
  public ResponseEntity<String> getEnv() {
    return ResponseEntity.ok(env);
  }
}
