package com.kuit.agarang.domain.memory.model.entity;


import com.kuit.agarang.global.common.model.entity.BaseEntity;
import com.kuit.agarang.domain.baby.model.entity.Baby;
import com.kuit.agarang.domain.member.model.entity.Member;
import com.kuit.agarang.domain.memory.enums.Genre;
import com.kuit.agarang.domain.memory.enums.Instrument;
import com.kuit.agarang.domain.memory.enums.Mood;
import com.kuit.agarang.domain.memory.enums.Tempo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "baby_id")
  private Baby baby;

  private String imageUrl;
  private String musicTitle;
  private String musicUrl;
  private String text;

  @Enumerated(EnumType.STRING)
  private Genre genre;

  @Enumerated(EnumType.STRING)
  private Mood mood;

  @Enumerated(EnumType.STRING)
  private Tempo tempo;

  @Enumerated(EnumType.STRING)
  private Instrument instrument;

  @OneToMany(mappedBy = "memory")
  private List<Hashtag> hashtag;

  @Builder
  public Memory(String imageUrl, String musicTitle, String musicUrl, String text, Genre genre,
                Mood mood, Tempo tempo, Instrument instrument) {
    this.imageUrl = imageUrl;
    this.musicTitle = musicTitle;
    this.musicUrl = musicUrl;
    this.text = text;
    this.genre = genre;
    this.mood = mood;
    this.tempo = tempo;
    this.instrument = instrument;
  }

  public void updateMemory(String text) {
    this.text = text;
  }
}
