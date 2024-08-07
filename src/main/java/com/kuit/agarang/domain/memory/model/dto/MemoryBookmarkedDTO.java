package com.kuit.agarang.domain.memory.model.dto;

import com.kuit.agarang.domain.memory.model.entity.Memory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MemoryBookmarkedDTO {
  private Memory memory;
  private boolean bookmarked;

}
