package com.kuit.agarang.domain.memory.model.dto;

import com.kuit.agarang.domain.memory.model.entity.Memory;
import com.kuit.agarang.global.common.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DailyMemoryDTO {
  private long id;
  private String date;
  private String imageUrl;

  public static DailyMemoryDTO from(Memory memory) {
    return new DailyMemoryDTO(memory.getId(),
            DateUtil.formatLocalDateTime(memory.getCreatedAt(), "yyyyMMdd"),
            memory.getImageUrl());
  }
}
