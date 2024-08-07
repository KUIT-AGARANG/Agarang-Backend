package com.kuit.agarang.domain.login.controller;

import com.kuit.agarang.domain.login.model.dto.ReissueDto;
import com.kuit.agarang.domain.login.service.JWTService;
import com.kuit.agarang.domain.login.utils.CookieUtil;
import com.kuit.agarang.global.common.model.dto.BaseResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

import static com.kuit.agarang.global.common.model.dto.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
public class ReissueController {

  private final JWTService jwtService;
  private final CookieUtil cookieUtil;

  @PostMapping("/reissue")
  public ResponseEntity<BaseResponse<ReissueDto>> reissue(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

    String refresh = null;
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("refresh")) {
        refresh = cookie.getValue();
      }
    }

    ReissueDto reissueDto = jwtService.reissueTokens(refresh);
    response.setHeader("Authorization", reissueDto.getNewAccessToken());
    response.addCookie(cookieUtil.createCookie("refresh", reissueDto.getNewRefreshToken()));
    return new ResponseEntity<>(new BaseResponse<>(reissueDto), SUCCESS.getHttpStatus());
  }
}