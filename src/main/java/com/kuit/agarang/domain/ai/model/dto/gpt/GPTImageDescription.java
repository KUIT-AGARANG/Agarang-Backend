package com.kuit.agarang.domain.ai.model.dto.gpt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GPTImageDescription {
  private String text;
  private List<String> noun;
}
