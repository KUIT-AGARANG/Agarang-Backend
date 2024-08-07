package com.kuit.agarang.domain.baby.model.entity;

import com.kuit.agarang.domain.member.model.entity.Member;
import com.kuit.agarang.global.common.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Baby extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "character_id")
  private Character character;

  @OneToMany(mappedBy = "baby", fetch = FetchType.LAZY)
  private List<Member> members = new ArrayList<>();

  @Column(nullable = false, unique = true)
  private String babyCode;

  private String name;
  private LocalDate dueDate;
  private Double weight;

  @Builder
  public Baby(String babyCode, String name, LocalDate dueDate, Double weight) {
    this.babyCode = babyCode;
    this.name = name;
    this.dueDate = dueDate;
    this.weight = weight;
  }

  public Baby(Long id, String babyCode, String name, LocalDate dueDate, Double weight) {
    this.id = id;
    this.babyCode = babyCode;
    this.name = name;
    this.dueDate = dueDate;
    this.weight = weight;
  }

  public void setCharacter(Character character) { this.character = character; }
  public void setName(String name) {
    this.name = name;
  }
  public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
  }
  public void setWeight(Double weight) { this.weight = weight; }
}
