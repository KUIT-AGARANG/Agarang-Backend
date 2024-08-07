package com.kuit.agarang.domain.playlist.repository;

import com.kuit.agarang.domain.playlist.model.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

}
