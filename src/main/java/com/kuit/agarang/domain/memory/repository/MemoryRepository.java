package com.kuit.agarang.domain.memory.repository;

import com.kuit.agarang.domain.baby.model.entity.Baby;
import com.kuit.agarang.domain.memory.model.dto.MemoryBookmarkedDTO;
import com.kuit.agarang.domain.memory.model.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory,Long> {
//  @Query("SELECT mm FROM Memory mm JOIN MemoryBookmark mbm WHERE Date(mm.createdAt) = :date AND mm.baby.id = :babyId")
  @Query("SELECT new com.kuit.agarang.domain.memory.model.dto.MemoryBookmarkedDTO(mm, " +
          "CASE WHEN mbm.id IS NOT NULL THEN true ELSE false END) " +
          "FROM Memory mm " +
          "LEFT JOIN MemoryBookmark mbm ON mm.id = mbm.memory.id " +
          "WHERE DATE(mm.createdAt) = :date AND mm.baby = :baby " +
          "ORDER BY mm.createdAt DESC")
  List<MemoryBookmarkedDTO> findByDateAndBabyForMemoryCard(LocalDate date, Baby baby);

  @Query("SELECT m.imageUrl FROM Memory m WHERE Date(m.createdAt) >= :startDate AND Date(m.createdAt) <= :endDate")
  List<String> findImageUrlsByDate(LocalDate startDate, LocalDate endDate);

  List<Memory> findByBabyOrderByCreatedAtDesc(Baby baby);
  List<Memory> findByBaby(Baby baby);
  List<Memory> findTop3ByBabyOrderByCreatedAtDesc(Baby baby); // 3개 이하 시 조회된 갯수 만큼만 반환
}
