package com.kuit.agarang.domain.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BabyDueDateRequest {
  private LocalDate date;
}
