package com.kuit.agarang.domain.memory.controller;

import com.kuit.agarang.domain.memory.model.dto.BookmarkRequest;
import com.kuit.agarang.domain.memory.model.dto.DeleteMemoryRequest;
import com.kuit.agarang.domain.memory.model.dto.ModifyMemoryRequest;
import com.kuit.agarang.domain.memory.enums.ViewType;
import com.kuit.agarang.domain.memory.model.dto.*;
import com.kuit.agarang.domain.memory.service.MemoryService;
import com.kuit.agarang.global.common.model.dto.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/memory")
@RequiredArgsConstructor
public class MemoryController {
  private final MemoryService memoryService;

  @Operation(summary = "다이어리 조회 (카드, 즐겨찾기, 월, 일)")
  @GetMapping
  public ResponseEntity<BaseResponse> getMemoriesByViewType(@ModelAttribute MemoryRequest memoryRequest) {
    String requestViewType = memoryRequest.getViewType();
    log.info("memoryRequest : " + memoryRequest);
    ViewType viewType = ViewType.findBy(requestViewType);

    if (viewType.equals(ViewType.CARD)) {
      DailyMemoryResponse dailyMemoryResponse = memoryService.findMemory(memoryRequest);
      return ResponseEntity.ok(new BaseResponse<>(dailyMemoryResponse));
    }

    if (viewType.equals(ViewType.DAILY)) {
      //TODO : 페이징 처리
      DailyMemoriesResponse dailyMemoriesResponse = memoryService.findDailyMemories();
      return ResponseEntity.ok(new BaseResponse<>(dailyMemoriesResponse));
    }

    if (viewType.equals(ViewType.MONTHLY)) {
      //TODO : 페이징 처리
      MonthlyMemoryResponse monthlyMemoryResponse = memoryService.findAllMonthlyThumbnails();
      return ResponseEntity.ok(new BaseResponse<>(monthlyMemoryResponse));
    }

    if (viewType.equals(ViewType.BOOKMARK)) {
      FavoriteMemoriesResponse favoriteMemoriesResponse = memoryService.findFavoriteMemories();
      return ResponseEntity.ok(new BaseResponse(favoriteMemoriesResponse));
    }
    throw new RuntimeException();
  }

  @PostMapping("/bookmark")
  public ResponseEntity<BaseResponse> setBookmark(@RequestBody BookmarkRequest bookmarkRequest) {
    memoryService.updateBookmark(bookmarkRequest);
    return ResponseEntity.ok(new BaseResponse());
  }

  @PutMapping
  public ResponseEntity<BaseResponse> modifyMemory(@RequestBody ModifyMemoryRequest modifyMemoryRequest) {
    memoryService.modifyMemory(modifyMemoryRequest);
    return ResponseEntity.ok(new BaseResponse());
  }

  @DeleteMapping
  public ResponseEntity<BaseResponse> deleteMemory(@RequestBody DeleteMemoryRequest deleteMemoryRequest) {
    memoryService.removeMemory(deleteMemoryRequest);
    return ResponseEntity.ok(new BaseResponse());
  }
}
