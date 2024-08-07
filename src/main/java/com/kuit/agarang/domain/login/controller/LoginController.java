package com.kuit.agarang.domain.login.controller;

import com.kuit.agarang.domain.login.model.dto.CustomOAuth2User;
import com.kuit.agarang.domain.member.model.dto.BabyCodeRequest;
import com.kuit.agarang.domain.member.model.dto.ProcessBabyRequest;
import com.kuit.agarang.domain.member.service.MemberService;
import com.kuit.agarang.global.common.model.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kuit.agarang.global.common.model.dto.BaseResponseStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

  private final MemberService memberService;

  @PostMapping("/baby-code")
  public ResponseEntity<BaseResponse<Void>> verifyBabyCode(@AuthenticationPrincipal CustomOAuth2User details,
                                                           @RequestBody BabyCodeRequest request) {
    memberService.verifyBabyCode(details.getMemberId(), request.getBabyCode());
    return ResponseEntity.ok(new BaseResponse<>(SUCCESS));
  }

  @PostMapping("/process-baby")
  public ResponseEntity<BaseResponse<Void>> processBabyAssignment(@AuthenticationPrincipal CustomOAuth2User details,
                                                                  @RequestBody ProcessBabyRequest request) {
    memberService.processBabyAssignment(details.getMemberId(), request.getBabyName(),
        request.getDueDate(), request.getFamilyRole());
    return ResponseEntity.ok(new BaseResponse<>(SUCCESS));
  }

}
