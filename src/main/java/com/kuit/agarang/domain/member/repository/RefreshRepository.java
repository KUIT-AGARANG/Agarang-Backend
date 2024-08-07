package com.kuit.agarang.domain.member.repository;

import com.kuit.agarang.domain.member.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshRepository extends JpaRepository<RefreshToken, Long> {

  Boolean existsByValue(String value);
  void deleteByValue(String value);
}
