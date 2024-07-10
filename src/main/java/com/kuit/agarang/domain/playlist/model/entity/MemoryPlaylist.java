package com.kuit.agarang.domain.playlist.model.entity;

import com.kuit.agarang.common.model.entity.BaseEntity;
import com.kuit.agarang.domain.memory.model.entity.Memory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoryPlaylist extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Playlist playlist;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn
  private Memory memory;
}
