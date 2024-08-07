package com.kuit.agarang.domain.home.controller;

import com.kuit.agarang.domain.home.model.dto.BabySettingResponse;
import com.kuit.agarang.domain.home.model.dto.BabySettingUpdateRequest;
import com.kuit.agarang.domain.home.model.dto.FamilySettingResponse;
import com.kuit.agarang.domain.home.model.dto.GlobalSettingResponse;
import com.kuit.agarang.domain.home.service.SettingService;
import com.kuit.agarang.global.common.model.dto.BaseResponse;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/home/setting")
@RequiredArgsConstructor
public class SettingController {

  private final SettingService settingService;

  @GetMapping()
  public ResponseEntity<BaseResponse<GlobalSettingResponse>> getSetting() {
    GlobalSettingResponse settingData = settingService.getGlobalSetting();
    return ResponseEntity.ok(new BaseResponse<>(settingData));
  }

  @GetMapping("/baby")
  public ResponseEntity<BaseResponse<BabySettingResponse>> getBabySetting() {
    BabySettingResponse settingData = settingService.getBabySetting();
    return ResponseEntity.ok(new BaseResponse<>(settingData));
  }

  @PutMapping("/baby")
  public ResponseEntity<BaseResponse> updateBabySetting(@RequestBody BabySettingUpdateRequest request) {
    settingService.updateBabySetting(request);
    return ResponseEntity.ok(new BaseResponse<>(BaseResponseStatus.SUCCESS));
  }

  @GetMapping("/family")
  public ResponseEntity<BaseResponse<FamilySettingResponse>> getFamilySetting() {
    FamilySettingResponse settingData = settingService.getFamilySetting();
    return ResponseEntity.ok(new BaseResponse<>(settingData));
  }
}
