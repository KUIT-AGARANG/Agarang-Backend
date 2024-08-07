package com.kuit.agarang.domain.memory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyMemoriesResponse {
  private List<DailyMemoryDTO> dailyMemories;
}
