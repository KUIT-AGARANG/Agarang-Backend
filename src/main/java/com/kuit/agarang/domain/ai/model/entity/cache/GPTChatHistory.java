package com.kuit.agarang.domain.ai.model.entity.cache;

import com.kuit.agarang.domain.ai.model.dto.MusicInfo;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTImageDescription;
import com.kuit.agarang.domain.ai.model.dto.gpt.GPTMessage;
import com.kuit.agarang.global.s3.model.dto.S3File;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class GPTChatHistory {
  private S3File image;
  private GPTImageDescription imageDescription;
  private List<GPTMessage> historyMessages;
  @Setter
  private MusicInfo musicInfo;

  @Builder
  public GPTChatHistory(S3File image, GPTImageDescription imageDescription, List<GPTMessage> historyMessages, MusicInfo musicInfo) {
    this.image = image;
    this.imageDescription = imageDescription;
    this.historyMessages = historyMessages;
    this.musicInfo = musicInfo;
  }
}
