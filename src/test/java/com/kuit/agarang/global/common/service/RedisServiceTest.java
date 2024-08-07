package com.kuit.agarang.global.common.service;

import com.kuit.agarang.domain.ai.model.dto.gpt.*;
import com.kuit.agarang.domain.ai.model.entity.cache.GPTChatHistory;
import com.kuit.agarang.domain.ai.model.enums.GPTRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisServiceTest {

  @Autowired
  private RedisService redisService;
  private static final String KEY = "agarang";

  @AfterEach
  void delete() {
    redisService.delete(KEY);
  }

  @Test
  void saveStringAndGet() {
    // given
    String value = "test-value";
    // when
    redisService.save(KEY, value);
    // then
    assertEquals(value, redisService.get(KEY, String.class).orElseThrow(() -> new RuntimeException("")));
  }

  @Test
  void updateString() {
    // given
    String value = "test-value";
    redisService.save(KEY, value);
    // when
    String newValue = "test2-value";
    redisService.save(KEY, newValue);
    // then
    assertEquals(newValue, redisService.get(KEY, String.class).orElseThrow(() -> new RuntimeException("")));
  }

  @Test
  void updateObject() {
    // given
    List<GPTMessage> messages = new ArrayList<>(List.of(
      GPTMessage.builder().role(GPTRole.USER).content("user-user").build()));

    GPTChatHistory chatHistory = GPTChatHistory.builder()
      .imageDescription(new GPTImageDescription("text", List.of("a", "b", "c")))
      .historyMessages(messages)
      .build();
    redisService.save(KEY, chatHistory);
    GPTChatHistory gptChatHistory = redisService.get(KEY, GPTChatHistory.class).get();

    // when
    GPTMessage newMessage = GPTMessage.builder().role(GPTRole.ASSISTANT).content("assistant-assistant").build();
    chatHistory.getHistoryMessages().add(newMessage);
    redisService.save(KEY, chatHistory);

    // then
    GPTChatHistory updatedGptChatHistory = redisService.get(KEY, GPTChatHistory.class).get();
    assertEquals(gptChatHistory.getImageDescription().getText(), updatedGptChatHistory.getImageDescription().getText());
    assertEquals(gptChatHistory.getImageDescription().getNoun(), updatedGptChatHistory.getImageDescription().getNoun());

    assertEquals(gptChatHistory.getHistoryMessages().get(0).getContent(),
      updatedGptChatHistory.getHistoryMessages().get(0).getContent());
    assertEquals(newMessage.getContent(), updatedGptChatHistory.getHistoryMessages().get(1).getContent());
  }

  @Test
  void saveStringWithExpiry() throws InterruptedException {
    // given
    String value = "test-value";
    redisService.saveWithExpiry(KEY, value, 1000L, TimeUnit.MILLISECONDS);
    // when
    Thread.sleep(1000);
    // then
    assertEquals(Optional.empty(), redisService.get(KEY, String.class));
  }

  @Test
  void deleteString() {
    // given
    String value = "test-value";
    redisService.save(KEY, value);
    // when
    redisService.delete(KEY);
    // then
    assertEquals(Optional.empty(), redisService.get(KEY, String.class));
  }

  @Test
  void existsByKey_true() {
    // given
    String value = "test-value";
    // when
    redisService.save(KEY, value);
    // then
    assertTrue(redisService.existsByKey(KEY));
  }

  @Test
  void existsByKey_false() {
    // given
    String value = "test-value";
    // when
    redisService.save(KEY, value);
    redisService.delete(KEY);
    // then
    assertFalse(redisService.existsByKey(KEY));
  }

  @Test
  void saveGPTChatAndGet() {
    // given
    // gpt 질문
    GPTMessage requestMessage = GPTMessage.builder()
      .role(GPTRole.USER)
      .content("오늘은 무슨 요일이야?")
      .build();
    GPTRequest request = new GPTRequest(new ArrayList<>(List.of(requestMessage)));

    // gpt 대답
    GPTMessage responseMessage = GPTMessage.builder()
      .role(GPTRole.ASSISTANT)
      .content("오늘은 화요일이야.")
      .build();
    GPTImageDescription imageDescription =
      new GPTImageDescription("맑고 푸른 하늘 아래 투명한 바다와 부드러운 모래사장이 어우러진 평화로운 해변 풍경입니다.", List.of("바다", "여름", "모래성"));

    request.getMessages().add(responseMessage); // 질문, 대답 합친 history message 포함해서 선언
    GPTChatHistory chatHistory = GPTChatHistory.builder()
      .imageDescription(imageDescription)
      .historyMessages(request.getMessages())
      .build();

    // when
    redisService.save(KEY, chatHistory);

    // then
    GPTChatHistory savedChatHistory = redisService.get(KEY, GPTChatHistory.class)
      .orElseThrow(() -> new RuntimeException(""));
    assertEquals(chatHistory.getImageDescription().getText(), savedChatHistory.getImageDescription().getText());
    assertEquals(chatHistory.getImageDescription().getNoun(), savedChatHistory.getImageDescription().getNoun());
    assertEquals(requestMessage.getContent(), savedChatHistory.getHistoryMessages().get(0).getContent());
    assertEquals(responseMessage.getContent(), savedChatHistory.getHistoryMessages().get(1).getContent());
  }
}