package com.kuit.agarang.domain.memory.model.dto;

import com.kuit.agarang.domain.memory.model.entity.Hashtag;
import com.kuit.agarang.domain.memory.model.entity.Memory;
import com.kuit.agarang.global.common.utils.DateUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MemoryDTO {
  private String writer;
  private String date;
  private String content;
  private String musicUrl;
  private List<String> hashTags;
  private boolean isBookmarked;

  @Builder
  public MemoryDTO(String content, String date, String musicUrl, List<String> hashTags, boolean isBookmarked, String writer) {
    this.content = content;
    this.date = date;
    this.musicUrl = musicUrl;
    this.hashTags = hashTags;
    this.isBookmarked = isBookmarked;
    this.writer = writer;
  }

  public static MemoryDTO of(Memory memory, boolean isBookmarked) {
    return MemoryDTO.builder()
            .writer(memory.getMember().getRole())
            .date(DateUtil.formatLocalDateTime(memory.getCreatedAt(), "yyyy. MM. dd"))
            .content(memory.getText())
            .musicUrl(memory.getMusicUrl())
            .hashTags(memory.getHashtag().stream()
                    .map(Hashtag::getName)
                    .toList())
            .isBookmarked(isBookmarked)
            .build();
  }
}
