package com.kuit.agarang.domain.memory.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MonthlyMemoryDTO {
  private String date;
  private String imageUrl;

  private MonthlyMemoryDTO(String date, String imageUrl) {
    this.date = date;
    this.imageUrl = imageUrl;
  }

  public static MonthlyMemoryDTO of(String date, String imageUrl) {
    return new MonthlyMemoryDTO(date, imageUrl);
  }
}
