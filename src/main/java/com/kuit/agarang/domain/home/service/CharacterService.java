package com.kuit.agarang.domain.home.service;

import com.kuit.agarang.domain.baby.model.entity.Baby;
import com.kuit.agarang.domain.baby.model.entity.Character;
import com.kuit.agarang.domain.baby.repository.BabyRepository;
import com.kuit.agarang.domain.baby.repository.CharacterRepository;
import com.kuit.agarang.domain.home.model.dto.CharacterSettingResponse;
import com.kuit.agarang.domain.login.utils.AuthenticationUtil;
import com.kuit.agarang.global.common.exception.exception.BusinessException;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CharacterService {

  private final CharacterRepository characterRepository;
  private final BabyRepository babyRepository;
  private final AuthenticationUtil authenticationUtil;

  public List<CharacterSettingResponse> getCharactersByDate() {

    String providerId = authenticationUtil.getProviderId();
    Baby baby = babyRepository.findByProviderId(providerId)
        .orElseThrow(() -> new BusinessException(BaseResponseStatus.NOT_FOUND_BABY));

    long dDay = ChronoUnit.DAYS.between(LocalDate.now(), baby.getDueDate());
    Integer level = dDay <= 140 ? 2 : 1;  // DDay 140일 이하면 레벨 2, 아니면 레벨 1

    List<Character> charactersByLevel = characterRepository.findByLevel(level);

    return charactersByLevel.stream()
        .map(character -> new CharacterSettingResponse(character.getId(), character.getName(),
            character.getDescription(), character.getImageUrl()))
        .collect(Collectors.toList());
  }

  public void updateCharacterSetting(Long characterId) {

    String providerId = authenticationUtil.getProviderId();

    // 현재 사용자의 아기(Baby) 정보 가져오기
    Baby baby = babyRepository.findByProviderId(providerId)
        .orElseThrow(() -> new BusinessException(BaseResponseStatus.NOT_FOUND_BABY));

    // 선택한 캐릭터가 존재하는지 확인
    Character character = characterRepository.findById(characterId)
        .orElseThrow(() -> new BusinessException(BaseResponseStatus.NOT_FOUND_CHARACTER));

    baby.setCharacter(character);

    babyRepository.save(baby);
  }
}
