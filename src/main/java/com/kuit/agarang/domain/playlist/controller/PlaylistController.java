package com.kuit.agarang.domain.playlist.controller;

import com.kuit.agarang.domain.playlist.model.dto.PlaylistTracksResponse;
import com.kuit.agarang.domain.playlist.model.dto.PlaylistsResponse;
import com.kuit.agarang.domain.playlist.service.PlaylistService;
import com.kuit.agarang.global.common.model.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/playlists")
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @GetMapping
    public ResponseEntity<BaseResponse<PlaylistsResponse>> getAllPlaylists() {
        PlaylistsResponse playlistsResponse = playlistService.getAllPlaylists();
        return ResponseEntity.ok(new BaseResponse<>(playlistsResponse));
    }

    @GetMapping("/{id}/track")
    public ResponseEntity<BaseResponse<PlaylistTracksResponse>> getPlaylistTracks(@PathVariable Long id,
                                                                                  @RequestParam Long memberId) {
        PlaylistTracksResponse playlistTracksResponse = playlistService.getPlaylistTracks(id, memberId);
        return ResponseEntity.ok(new BaseResponse<>(playlistTracksResponse));
    }
}
