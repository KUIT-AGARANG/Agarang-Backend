package com.kuit.agarang.domain.ai.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTChat;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTContent;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTMessage;
import com.kuit.agarang.domain.ai.model.enums.GPTRole;
import com.kuit.agarang.domain.ai.model.enums.GPTSystemRole;
import com.kuit.agarang.global.common.exception.exception.OpenAPIException;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GPTUtil {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public GPTMessage createImageQuestion(String prompt, String imageUrl) {
    return GPTMessage.builder()
      .role(GPTRole.USER)
      .content(List.of(
        GPTContent.createTextContent(prompt), GPTContent.createImageContent(imageUrl)))
      .build();
  }

  public GPTMessage createSystemMessage(GPTSystemRole role) {
    return GPTMessage.builder()
      .role(GPTRole.SYSTEM)
      .content(role.getText())
      .build();
  }

  public GPTMessage createTextMessage(String prompt) {
    return GPTMessage.builder()
      .role(GPTRole.USER)
      .content(prompt)
      .build();
  }

  public List<GPTMessage> createHistoryMessage(GPTChat gptChat) {
    List<GPTMessage> historyMessage = gptChat.getGptRequest().getMessages();
    historyMessage.add(getResponseMessage(gptChat));
    return historyMessage;
  }

  public String getGPTAnswer(GPTChat gptChat) {
    try {
      return (String) getResponseMessage(gptChat).getContent();
    } catch (NullPointerException e) {
      throw new OpenAPIException(BaseResponseStatus.INVALID_GPT_RESPONSE);
    }
  }

  private GPTMessage getResponseMessage(GPTChat gptChat) {
    try {
      return gptChat.getGptResponse().getChoices().get(0).getMessage();
    } catch (NullPointerException e) {
      throw new OpenAPIException(BaseResponseStatus.INVALID_GPT_RESPONSE);
    }
  }

  public <T> T parseJson(GPTChat chat, Class<T> clazz) {
    String jsonString = getGPTAnswer(chat);
    try {
      return objectMapper.readValue(jsonString, clazz);
    } catch (JsonProcessingException e) {
      log.info("invalid gpt's json answer (clazz) : {}", jsonString);
      throw new OpenAPIException(BaseResponseStatus.INVALID_GPT_RESPONSE);
    }
  }

  public String parseJson(GPTChat chat, String filedName) {
    String jsonString = getGPTAnswer(chat);
    try {
      return objectMapper.readTree(jsonString).get(filedName).asText();
    } catch (JsonProcessingException e) {
      log.info("invalid gpt's json answer (string) : {}", jsonString);
      throw new OpenAPIException(BaseResponseStatus.INVALID_GPT_RESPONSE);
    }
  }
}
