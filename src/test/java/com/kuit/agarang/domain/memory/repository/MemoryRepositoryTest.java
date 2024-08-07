package com.kuit.agarang.domain.memory.repository;

import com.kuit.agarang.domain.baby.model.entity.Baby;
import com.kuit.agarang.domain.memory.model.dto.MemoryBookmarkedDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
class MemoryRepositoryTest {

  @Autowired
  MemoryRepository memoryRepository;

  @Test
  void getData() {
    //given
    LocalDate localDate = LocalDate.of(2024, 7, 1);
    Baby baby = new Baby(1L, "DXW1234", "아가", LocalDate.of(2025, 1, 1), 1.8D);
    //when
    List<MemoryBookmarkedDTO> memories = memoryRepository.findByDateAndBabyForMemoryCard(localDate, baby);
    //then
    Assertions.assertThat(memories.size())
            .isEqualTo(1);
  }
}