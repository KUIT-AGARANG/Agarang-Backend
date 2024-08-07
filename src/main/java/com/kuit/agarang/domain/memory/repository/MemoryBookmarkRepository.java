package com.kuit.agarang.domain.memory.repository;

import com.kuit.agarang.domain.member.model.entity.Member;
import com.kuit.agarang.domain.memory.model.entity.Memory;
import com.kuit.agarang.domain.memory.model.entity.MemoryBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemoryBookmarkRepository extends JpaRepository<MemoryBookmark, Long> {

  @Query("SELECT m FROM MemoryBookmark mb join Memory m on m.id = mb.memory.id WHERE m.member = :member")
  List<Memory> findMemoryBookmarksByMember(Member member);

  Optional<MemoryBookmark> findByMemoryAndMember(Memory memory, Member member);

  void deleteByMemory(Memory memory);
}
