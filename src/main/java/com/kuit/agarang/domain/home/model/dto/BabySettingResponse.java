package com.kuit.agarang.domain.home.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BabySettingResponse {

  private String babyName;
  private LocalDate dueDate;
  private Double weight;
}
