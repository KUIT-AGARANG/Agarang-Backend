package com.kuit.agarang.domain.home.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class GlobalSettingResponse {

  private String babyName;
  private Integer dDay;
  private LocalDate dueDate;
}
