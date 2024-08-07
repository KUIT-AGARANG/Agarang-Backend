package com.kuit.agarang.domain.memory.model.entity;

import com.kuit.agarang.global.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memory_id")
  private Memory memory;

  private String name; // 해시태그 명

  @Builder
  public Hashtag(String name) {
    this.name = name;
  }
}
