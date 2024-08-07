package com.kuit.agarang.domain.playlist.model.dto;

import com.kuit.agarang.domain.playlist.model.entity.Playlist;
import lombok.*;

import lombok.Getter;

@Getter
@NoArgsConstructor
public class PlaylistDto {

    private Long id;
    private String name;
    private String imageUrl;

    @Builder
    public PlaylistDto(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static PlaylistDto of(Playlist playlist) {
        return PlaylistDto.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .imageUrl(playlist.getImageUrl())
                .build();
    }
}