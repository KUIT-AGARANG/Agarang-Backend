package com.kuit.agarang.domain.memory.repository;

import com.kuit.agarang.domain.memory.model.entity.Memory;
import com.kuit.agarang.domain.memory.model.entity.MusicBookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicBookmarkRepository extends JpaRepository<MusicBookmark, Long> {
    MusicBookmark findByMemory(Memory memory);
    boolean existsByMemory(Memory memory);
    void deleteByMemory(Memory memory);
}