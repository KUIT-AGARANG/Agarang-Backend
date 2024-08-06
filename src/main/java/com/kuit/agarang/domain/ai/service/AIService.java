package com.kuit.agarang.domain.ai.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuit.agarang.domain.ai.model.dto.*;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTChat;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTImageDescription;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTMessage;
import com.kuit.agarang.domain.ai.model.entity.cache.GPTChatHistory;
import com.kuit.agarang.domain.ai.model.enums.GPTSystemRole;
import com.kuit.agarang.domain.ai.utils.GPTPromptUtil;
import com.kuit.agarang.domain.ai.utils.GPTUtil;
import com.kuit.agarang.domain.baby.model.entity.Character;
import com.kuit.agarang.global.common.exception.exception.OpenAPIException;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import com.kuit.agarang.global.common.service.RedisService;
import com.kuit.agarang.global.s3.model.dto.S3File;
import com.kuit.agarang.global.s3.utils.S3FileUtil;
import com.kuit.agarang.global.s3.utils.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AIService {

  private final GPTUtil gptUtil;
  private final GPTPromptUtil promptUtil;
  private final GPTChatService gptChatService;
  private final TypecastService typecastService;

  private final S3Util s3Util;
  private final S3FileUtil s3FileUtil;

  private final RedisService redisService;
  private final ObjectMapper objectMapper;

  public QuestionResponse getFirstQuestion(MultipartFile image) throws Exception {
    S3File s3File = s3FileUtil.uploadTempFile(image);

    // image -> gpt -> 노래제목, 해시태그 생성
    String prompt = promptUtil.createImageDescriptionPrompt();
    GPTChat imageChat = gptChatService.chatWithImage(s3File, prompt);
    GPTImageDescription imageDescription = GPTImageDescription.from(gptUtil.getGPTAnswer(imageChat));

    // 해시태그 -> gpt ->  질문1 생성
    prompt = promptUtil.createImageQuestionPrompt(imageDescription);
    GPTChat questionChat = gptChatService.chat(GPTSystemRole.COUNSELOR, prompt, 0L);
    String question = gptUtil.getGPTAnswer(questionChat);

    // 질문1 -> tts -> 오디오 변환
    String questionAudioUrl = getAudioUrl(question);

    // 대화기록 저장 및 임시저장이 필요한 데이터 저장
    String redisKey = questionChat.getGptResponse().getId();
    List<GPTMessage> historyMessage = gptUtil.createHistoryMessage(questionChat);
    redisService.save(redisKey,
      GPTChatHistory.builder()
        .imageTempPath(s3File.getFilename())
        .hashtags(imageDescription.getNoun())
        .historyMessages(historyMessage)
        .build());

    logChat(historyMessage);
    return new QuestionResponse(QuestionResult.builder()
      .id(redisKey)
      .text(question)
      .audioUrl(questionAudioUrl)
      .build());
  }

  private @Nullable String getAudioUrl(String question) {
    String typecastAudioId = typecastService.getAudioDownloadUrl(question);
    String questionAudioUrl = null;
    if (checkEntityExistence(typecastAudioId)) {
      questionAudioUrl = redisService.get(typecastAudioId, String.class)
        .orElseThrow(() -> new OpenAPIException(BaseResponseStatus.NOT_FOUND_TSS_AUDIO));
      redisService.delete(typecastAudioId);
    }
    return questionAudioUrl;
  }

  public QuestionResponse getNextQuestion(TextAnswer answer) {
    GPTChatHistory chatHistory = redisService.get(answer.getId(), GPTChatHistory.class)
      .orElseThrow(() -> new OpenAPIException(BaseResponseStatus.NOT_FOUND_HISTORY_CHAT));

    GPTChat chat = gptChatService.chatWithHistory(chatHistory.getHistoryMessages(), answer.getText(), 0L);
    String question = gptUtil.getGPTAnswer(chat);

    String questionAudioUrl = getAudioUrl(question);

    logChat(gptUtil.createHistoryMessage(chat));
    redisService.save(answer.getId(), chatHistory);

    return new QuestionResponse(QuestionResult.builder()
      .id(answer.getId())
      .text(question)
      .audioUrl(questionAudioUrl)
      .build());
  }

  public void saveLastAnswer(TextAnswer answer) {
    GPTChatHistory chatHistory = redisService.get(answer.getId(), GPTChatHistory.class)
      .orElseThrow(() -> new OpenAPIException(BaseResponseStatus.NOT_FOUND_HISTORY_CHAT));

    chatHistory.getHistoryMessages().add(gptUtil.createTextMessage(answer.getText()));
    logChat(chatHistory.getHistoryMessages());
    redisService.save(answer.getId(), chatHistory);
  }

  @Async
  public void createMemoryText(String gptChatHistoryId) {
    GPTChatHistory chatHistory = redisService.get(gptChatHistoryId, GPTChatHistory.class)
      .orElseThrow(() -> new OpenAPIException(BaseResponseStatus.NOT_FOUND_HISTORY_CHAT));

    // TODO : memberId 로 필드 조회
    String prompt = promptUtil.createMemoryTextPrompt("뿌둥", "아빠");
    GPTChat chat = gptChatService.chatWithHistory(chatHistory.getHistoryMessages(), prompt, 0L);

    logChat(gptUtil.createHistoryMessage(chat));
    redisService.save(gptChatHistoryId, chatHistory);
  }

  public void saveMusicChoice(MusicAnswer answer) {
    GPTChatHistory chatHistory = redisService.get(answer.getId(), GPTChatHistory.class)
      .orElseThrow(() -> new OpenAPIException(BaseResponseStatus.NOT_FOUND_HISTORY_CHAT));

    chatHistory.setMusicInfo(MusicInfo.from(answer.getMusicChoice()));
    log.info("music info : {}, {}, {}, {}",
      chatHistory.getMusicInfo().getInstrument(), chatHistory.getMusicInfo().getGenre(),
      chatHistory.getMusicInfo().getMood(), chatHistory.getMusicInfo().getTempo());
    redisService.save(answer.getId(), chatHistory);
  }

  public String getCharacterBubble(Character character, String familyRole) {
    String prompt = promptUtil.createCharacterBubble(character, familyRole);
    GPTChat chat = gptChatService.chat(GPTSystemRole.ASSISTANT, prompt, 1L);
    return gptUtil.getGPTAnswer(chat);
  }

  // TODO : redis 트리거 전환
  public boolean checkEntityExistence(String key) {
    try {
      for (int i = 1; i < 3; i++) {
        log.info("{}차 대기", i);
        Thread.sleep(1500);

        if (redisService.existsByKey(key)) {
          return true;
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // 인터럽트 상태를 복구
    }
    return false;
  }

  private void logChat(List<GPTMessage> historyMessage) {
    try {
      log.info(objectMapper.writeValueAsString(historyMessage));
    } catch (Exception e) {
      log.info("채팅 로길 실패");
    }
  }
}
