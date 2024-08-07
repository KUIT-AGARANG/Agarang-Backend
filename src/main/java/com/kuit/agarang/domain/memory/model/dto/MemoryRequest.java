package com.kuit.agarang.domain.memory.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemoryRequest {
  private String viewType;
  private String date;
  private int page;
  private int size;

  @Override
  public String toString() {
    return "MemoryRequest{" +
            "date='" + date + '\'' +
            ", viewType='" + viewType + '\'' +
            ", page=" + page +
            ", size=" + size +
            '}';
  }
}
