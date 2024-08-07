package com.kuit.agarang.domain.ai.controller;

import com.kuit.agarang.domain.ai.model.dto.typecast.TypecastWebhookResponse;
import com.kuit.agarang.domain.ai.service.TypecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/tts")
@RequiredArgsConstructor
public class TypecastController {

  private final TypecastService typecastService;

  @PostMapping("/webhook")
  public void webhook(@RequestBody TypecastWebhookResponse response) {
    typecastService.saveAudio(response);
  }
}
