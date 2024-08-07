package com.kuit.agarang.domain.playlist.model.entity;

import com.kuit.agarang.global.common.model.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playlist extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String imageUrl;

  @Builder
  public Playlist(String name, String imageUrl) {
    this.name = name;
    this.imageUrl = imageUrl;
  }
}
