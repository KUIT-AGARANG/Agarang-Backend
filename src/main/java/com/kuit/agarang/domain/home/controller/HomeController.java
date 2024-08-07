package com.kuit.agarang.domain.home.controller;

import com.kuit.agarang.domain.home.model.dto.HomeResponse;
import com.kuit.agarang.domain.home.service.HomeService;
import com.kuit.agarang.global.common.model.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {

  private final HomeService homeService;

  @GetMapping
  public ResponseEntity<BaseResponse<HomeResponse>> getHome() {
    HomeResponse homeData = homeService.getHome();
    return ResponseEntity.ok(new BaseResponse<>(homeData));
  }

}
