package com.kuit.agarang.domain.playlist.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteMusicRequest {
    private long playlistId;
    private long memoryId;
}
