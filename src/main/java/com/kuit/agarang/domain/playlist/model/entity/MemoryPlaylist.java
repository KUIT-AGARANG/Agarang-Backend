package com.kuit.agarang.domain.playlist.model.entity;

import com.kuit.agarang.global.common.model.entity.BaseEntity;
import com.kuit.agarang.domain.memory.model.entity.Memory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoryPlaylist extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "playlist_id")
  private Playlist playlist;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memory_id")
  private Memory memory;

  public MemoryPlaylist(Memory memory, Playlist playlist) {
    this.playlist = playlist;
    this.memory = memory;
  }
}
