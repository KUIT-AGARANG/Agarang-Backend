package com.kuit.agarang.domain.playlist.repository;

import com.kuit.agarang.domain.memory.model.entity.Memory;
import com.kuit.agarang.domain.playlist.model.entity.MemoryPlaylist;
import com.kuit.agarang.domain.playlist.model.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryPlaylistRepository extends JpaRepository<MemoryPlaylist, Long> {
  List<MemoryPlaylist> findByPlaylistId(Long playlistId);
  List<MemoryPlaylist> findByMemoryIn(List<Memory> memories);
  MemoryPlaylist findByMemoryAndPlaylist(Memory memory, Playlist playlist);
  void deleteByMemory(Memory memory);
  void deleteByMemoryAndPlaylist(Memory memory, Playlist playlist);
}
