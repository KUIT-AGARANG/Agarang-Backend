package com.kuit.agarang.domain.playlist.model.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlaylistTracksResponse {
    private PlaylistDto playlist;
    private List<MusicDto> tracks;
}