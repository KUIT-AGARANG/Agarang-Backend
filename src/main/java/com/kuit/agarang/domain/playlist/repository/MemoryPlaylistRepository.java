package com.kuit.agarang.domain.playlist.repository;

import com.kuit.agarang.domain.memory.model.entity.Memory;
import com.kuit.agarang.domain.playlist.model.entity.MemoryPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryPlaylistRepository extends JpaRepository<MemoryPlaylist, Long> {
  List<MemoryPlaylist> findByPlaylistId(Long playlistId);
  void deleteByMemory(Memory memory);

}
