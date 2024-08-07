package com.kuit.agarang.domain.playlist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistsResponse {
    private List<PlaylistDto> playlists;
}