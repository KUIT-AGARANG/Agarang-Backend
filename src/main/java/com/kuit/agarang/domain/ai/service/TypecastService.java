package com.kuit.agarang.domain.ai.service;

import com.kuit.agarang.domain.ai.model.dto.MessageRequest;
import com.kuit.agarang.domain.ai.model.dto.TypecastRequest;
import com.kuit.agarang.domain.ai.model.dto.TypecastResponse;
import com.kuit.agarang.domain.ai.model.dto.TypecastWebhookResponse;
import com.kuit.agarang.domain.ai.model.entity.TypecastAudio;
import com.kuit.agarang.domain.ai.model.repository.TypecastAudioRepository;
import com.kuit.agarang.domain.ai.utils.TypecastClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypecastService {

  @Value("${typecast.actorId}")
  private String actorId;
  private final TypecastClientUtil typeCastClientUtil;
  private final TypecastAudioRepository typecastAudioRepository;

  public void getAudioDownloadUrl(MessageRequest request) {
    typeCastClientUtil.post("/api/speak", TypecastRequest.create(request, actorId), TypecastResponse.class);
  }

  public void saveAudio(TypecastWebhookResponse response) {
    if ("failed".equals(response.getStatus())) {
      // TODO : 예외처리 (done or failed)
    }
    // TODO : redis cache 저장으로 변경 (일회성 데이터)
    typecastAudioRepository.save(new TypecastAudio(response.getAudioDownloadUrl()));
  }
}
