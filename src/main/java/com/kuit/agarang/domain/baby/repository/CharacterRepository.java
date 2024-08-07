package com.kuit.agarang.domain.baby.repository;

import com.kuit.agarang.domain.baby.model.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, Long> {

  List<Character> findByLevel(Integer level);

}
