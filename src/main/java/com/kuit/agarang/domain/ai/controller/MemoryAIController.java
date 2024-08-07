package com.kuit.agarang.domain.ai.controller;

import com.kuit.agarang.domain.ai.model.dto.MusicAnswer;
import com.kuit.agarang.domain.ai.model.dto.TextAnswer;
import com.kuit.agarang.domain.ai.model.dto.QuestionResponse;
import com.kuit.agarang.domain.ai.model.entity.cache.GPTChatHistory;
import com.kuit.agarang.domain.ai.service.AIService;
import com.kuit.agarang.global.common.model.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/memory/ai")
@RequiredArgsConstructor
public class MemoryAIController {

  private final AIService AIService;

  @PostMapping("/image-to-question")
  public ResponseEntity<BaseResponse<QuestionResponse>> getImageQuestion(@RequestParam MultipartFile image) throws Exception {
    return ResponseEntity.ok(new BaseResponse<>(AIService.getFirstQuestion(image)));
  }

  @PostMapping("/first-ans")
  public ResponseEntity<BaseResponse<QuestionResponse>> getNextQuestion(@RequestBody TextAnswer answer) {
    return ResponseEntity.ok(new BaseResponse<>(AIService.getNextQuestion(answer)));
  }

  @PostMapping("/second-ans")
  public ResponseEntity<BaseResponse<Void>> saveLastAnswer(@RequestBody TextAnswer answer) {
    AIService.saveLastAnswer(answer);
    AIService.createMemoryText(answer.getId());
    return ResponseEntity.ok(new BaseResponse<>());
  }

  @PostMapping("/music")
  public ResponseEntity<BaseResponse<Void>> saveLastAnswer(@RequestBody MusicAnswer answer) {
    GPTChatHistory chatHistory = AIService.setMusicChoice(answer);
    AIService.createMusicGenPrompt(chatHistory);
    return ResponseEntity.ok(new BaseResponse<>());
  }
}
