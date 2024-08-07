package com.kuit.agarang.domain.memory.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoryImageDTO {
  private long id;
  private String imageUrl;

  @Builder
  public MemoryImageDTO(long id, String imageUrl) {
    this.id = id;
    this.imageUrl = imageUrl;
  }

  public static MemoryImageDTO of(long id, String imageUrl) {
    return MemoryImageDTO.builder()
            .id(id)
            .imageUrl(imageUrl)
            .build();
  }
}
