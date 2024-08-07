package com.kuit.agarang.domain.ai.service;

import com.kuit.agarang.domain.ai.model.dto.typecast.TypecastRequest;
import com.kuit.agarang.domain.ai.model.dto.typecast.TypecastResponse;
import com.kuit.agarang.domain.ai.model.dto.typecast.TypecastWebhookResponse;
import com.kuit.agarang.domain.ai.utils.TypecastClientUtil;
import com.kuit.agarang.global.common.exception.exception.OpenAPIException;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import com.kuit.agarang.global.common.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TypecastService {

  @Value("${typecast.actorId}")
  private String actorId;
  private final TypecastClientUtil typeCastClientUtil;
  private final RedisService redisService;

  public String getAudioDownloadUrl(String text) {
    TypecastResponse response
      = typeCastClientUtil.post("/api/speak", TypecastRequest.create(text, actorId), TypecastResponse.class);
    try {
      return response.getResult().getSpeakUrl();
    } catch (NullPointerException e) {
      throw new OpenAPIException(BaseResponseStatus.NOT_FOUND_TSS_AUDIO);
    }
  }

  public void saveAudio(TypecastWebhookResponse response) {
    if ("failed".equals(response.getStatus())) {
      throw new OpenAPIException(BaseResponseStatus.NOT_FOUND_TSS_AUDIO);
    }
    redisService.save(response.getSpeakUrl(), response.getAudioDownloadUrl());
  }
}
