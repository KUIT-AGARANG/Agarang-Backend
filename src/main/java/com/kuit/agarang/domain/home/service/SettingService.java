package com.kuit.agarang.domain.home.service;

import com.kuit.agarang.domain.baby.model.entity.Baby;
import com.kuit.agarang.domain.baby.repository.BabyRepository;
import com.kuit.agarang.domain.home.model.dto.BabySettingResponse;
import com.kuit.agarang.domain.home.model.dto.BabySettingUpdateRequest;
import com.kuit.agarang.domain.home.model.dto.FamilySettingResponse;
import com.kuit.agarang.domain.home.model.dto.GlobalSettingResponse;
import com.kuit.agarang.domain.login.utils.AuthenticationUtil;
import com.kuit.agarang.domain.member.model.dto.MemberDTO;
import com.kuit.agarang.global.common.exception.exception.BusinessException;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SettingService {

  private final AuthenticationUtil authenticationUtil;
  private final BabyRepository babyRepository;

  public GlobalSettingResponse getGlobalSetting() {

    // 아기 이름
    String providerId = authenticationUtil.getProviderId();
    Baby baby = babyRepository.findByProviderId(providerId)
        .orElseThrow(() -> new BusinessException(BaseResponseStatus.NOT_FOUND_BABY));
    String babyName = baby.getName();

    // 예정일
    LocalDate dueDate = baby.getDueDate();

    // 디데이
    LocalDate today = LocalDate.now();
    Integer dDay = (int) ChronoUnit.DAYS.between(today, dueDate);

    return GlobalSettingResponse.builder()
        .babyName(babyName)
        .dDay(dDay)
        .dueDate(dueDate).build();
  }

  public BabySettingResponse getBabySetting() {

    // 아기 이름
    String providerId = authenticationUtil.getProviderId();
    Baby baby = babyRepository.findByProviderId(providerId)
        .orElseThrow(() -> new BusinessException(BaseResponseStatus.NOT_FOUND_BABY));
    String babyName = baby.getName();

    // 예정일
    LocalDate dueDate = baby.getDueDate();

    // 아기 체중
    Double weight = baby.getWeight();

    return BabySettingResponse.builder()
        .babyName(babyName)
        .dueDate(dueDate)
        .weight(weight).build();
  }

  @Transactional
  public void updateBabySetting(BabySettingUpdateRequest updateRequest) {
    String providerId = authenticationUtil.getProviderId();
    Baby baby = babyRepository.findByProviderId(providerId)
        .orElseThrow(() -> new BusinessException(BaseResponseStatus.NOT_FOUND_BABY));

    Optional.ofNullable(updateRequest.getBabyName())
        .ifPresent(baby::setName);

    Optional.ofNullable(updateRequest.getDueDate())
        .ifPresent(baby::setDueDate);

    Optional.ofNullable(updateRequest.getWeight())
        .ifPresent(baby::setWeight);

    babyRepository.save(baby);
  }

  public FamilySettingResponse getFamilySetting() {

    // 아기 코드
    String providerId = authenticationUtil.getProviderId();
    Baby baby = babyRepository.findByProviderId(providerId)
        .orElseThrow(() -> new BusinessException(BaseResponseStatus.NOT_FOUND_BABY));
    String babyName = baby.getBabyCode();

    // 가족
    List<MemberDTO> memberDTOs = baby.getMembers().stream()
        .map(member -> MemberDTO.builder()
            .name(member.getName())
            .role(member.getRole())
            .providerId(member.getProviderId())
            .build())
        .collect(Collectors.toList());

    return FamilySettingResponse.builder()
        .babyCode(babyName)
        .members(memberDTOs).build();
  }

}
