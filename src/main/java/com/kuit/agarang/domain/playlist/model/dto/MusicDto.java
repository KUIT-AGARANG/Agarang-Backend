package com.kuit.agarang.domain.playlist.model.dto;

import com.kuit.agarang.domain.memory.model.entity.Hashtag;
import com.kuit.agarang.domain.memory.model.entity.Memory;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
public class MusicDto {
    private String imageUrl;
    private String musicTitle;
    private String musicUrl;
    private List<String> hashTags;
    private boolean isBookmarked;

    @Builder
    public MusicDto(String imageUrl, String musicTitle, String musicUrl, List<String> hashTags, boolean isBookmarked) {
        this.imageUrl = imageUrl;
        this.musicTitle = musicTitle;
        this.musicUrl = musicUrl;
        this.hashTags = hashTags;
        this.isBookmarked = isBookmarked;
    }

    public static MusicDto of(Memory memory, boolean isBookmarked) {
        return MusicDto.builder()
                .imageUrl(memory.getImageUrl())
                .musicTitle(memory.getMusicTitle())
                .musicUrl(memory.getMusicUrl())
                .hashTags(memory.getHashtag().stream()
                        .map(Hashtag::getName)
                        .toList())
                .isBookmarked(isBookmarked)
                .build();
    }
}
