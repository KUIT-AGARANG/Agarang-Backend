package com.kuit.agarang.domain.ai.service;

import com.kuit.agarang.domain.ai.model.dto.gpt.GPTChat;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTMessage;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTRequest;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTResponse;
import com.kuit.agarang.domain.ai.model.enums.GPTSystemRole;
import com.kuit.agarang.domain.ai.utils.GPTUtil;
import com.kuit.agarang.domain.ai.utils.GptClientUtil;
import com.kuit.agarang.global.s3.model.dto.S3File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GPTChatService {

  private final GptClientUtil gptClientUtil;
  private final GPTUtil gptUtil;

  public GPTChat chatWithImage(S3File image, String prompt) {
    GPTMessage systemMessage = gptUtil.createSystemMessage(GPTSystemRole.IMAGE_DESCRIBER);
    GPTMessage imageQuestion = gptUtil.createImageQuestion(prompt, image.toGPTImageUrl());

    GPTRequest request = new GPTRequest(List.of(systemMessage, imageQuestion));
    request.setRequiredJson(true);
    GPTResponse response = gptClientUtil.post(request, GPTResponse.class);
    return new GPTChat(request, response);
  }

  public GPTChat chat(GPTSystemRole systemRole, String prompt, Long temperature, boolean requiredJson) {
    GPTMessage systemMessage = gptUtil.createSystemMessage(systemRole);
    GPTMessage message = gptUtil.createTextMessage(prompt);

    GPTRequest request = new GPTRequest(new ArrayList<>(List.of(systemMessage, message)));
    request.setTemperature(temperature);
    request.setRequiredJson(requiredJson);
    GPTResponse response = gptClientUtil.post(request, GPTResponse.class);
    return new GPTChat(request, response);
  }

  public GPTChat chatWithHistory(List<GPTMessage> historyMessage, String text, Long temperature) {
    historyMessage.add(gptUtil.createTextMessage(text));
    GPTRequest request = new GPTRequest(historyMessage);
    request.setTemperature(temperature);
    GPTResponse response = gptClientUtil.post(request, GPTResponse.class);
    return new GPTChat(request, response);
  }
}
