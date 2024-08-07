package com.kuit.agarang.domain.home.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BabySettingUpdateRequest {

  private String babyName;
  private LocalDate dueDate;
  private Double weight;
}