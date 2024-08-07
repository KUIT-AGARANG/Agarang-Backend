package com.kuit.agarang.domain.home.controller;

import com.kuit.agarang.domain.home.model.dto.BabySettingUpdateRequest;
import com.kuit.agarang.domain.home.model.dto.CharacterChangeRequest;
import com.kuit.agarang.domain.home.model.dto.CharacterSettingResponse;
import com.kuit.agarang.domain.home.model.dto.GlobalSettingResponse;
import com.kuit.agarang.domain.home.service.CharacterService;
import com.kuit.agarang.global.common.model.dto.BaseResponse;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home/setting/character")
@RequiredArgsConstructor
public class CharacterController {

  private final CharacterService characterService;

  @GetMapping
  public ResponseEntity<BaseResponse<List<CharacterSettingResponse>>> getCharactersByDate() {
    List<CharacterSettingResponse> charactersByDate = characterService.getCharactersByDate();
    return ResponseEntity.ok(new BaseResponse(charactersByDate));

  }

  @PutMapping
  public ResponseEntity<BaseResponse> updateBabyCharacter(@RequestBody CharacterChangeRequest request) {
    characterService.updateCharacterSetting(request.getCharacterId());
    return ResponseEntity.ok(new BaseResponse<>(BaseResponseStatus.SUCCESS));
  }
}
