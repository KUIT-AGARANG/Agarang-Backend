package com.kuit.agarang.domain.memory.model.entity;

import com.kuit.agarang.global.common.model.entity.BaseEntity;
import com.kuit.agarang.domain.member.model.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoryBookmark extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memory_id")
  private Memory memory;

  public MemoryBookmark(Member member, Memory memory) {
    this.member = member;
    this.memory = memory;
  }
}
