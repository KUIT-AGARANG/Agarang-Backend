package com.kuit.agarang.domain.login.service;

import com.kuit.agarang.domain.login.model.dto.ReissueDto;
import com.kuit.agarang.domain.login.utils.JWTUtil;
import com.kuit.agarang.domain.member.model.entity.RefreshToken;
import com.kuit.agarang.domain.member.repository.RefreshRepository;
import com.kuit.agarang.global.common.exception.exception.BusinessException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static com.kuit.agarang.global.common.model.dto.BaseResponseStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
@Transactional
public class JWTService {

  private final JWTUtil jwtUtil;
  private final RefreshRepository refreshRepository;

  @Value("${secret.jwt-refresh-expired-in}")
  private Long REFRESH_EXPIRED_IN;

  // TODO : Exception 추가
  public ReissueDto reissueTokens(String refresh) {

    validateRefreshToken(refresh);

    String providerId = jwtUtil.getProviderId(refresh);
    String role = jwtUtil.getRole(refresh);
    Long memberId = jwtUtil.getMemberId(refresh);

    // AccessToken 생성 및 Refresh Rotate
    String newAccess = jwtUtil.createAccessToken(providerId, role, memberId);
    String newRefresh = jwtUtil.createRefreshToken(providerId, role, memberId);

    // 기존 토큰 삭제 후 새로운 Refresh Token 저장
    refreshRepository.deleteByValue(refresh);
    addRefreshEntity(providerId, newRefresh);

    return ReissueDto.builder()
        .newAccessToken(newAccess)
        .newRefreshToken(newRefresh)
        .providerId(providerId)
        .role(role)
        .build();
  }

  public void addRefreshEntity(String providerId, String value) {
    Date date = new Date(System.currentTimeMillis() + REFRESH_EXPIRED_IN);

    RefreshToken refreshToken = RefreshToken.builder()
        .providerId(providerId)
        .value(value)
        .expiration(date.toString()).build();

    refreshRepository.save(refreshToken);
  }

  private void validateRefreshToken(String refresh) {

    if (refresh == null) {
      throw new BusinessException(BAD_REQUEST);
    }

    // 만료 여부 체크
    try {
      jwtUtil.isExpired(refresh);
    } catch (ExpiredJwtException e) {
      throw new BusinessException(BAD_REQUEST);
    }

    // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
    String category = jwtUtil.getCategory(refresh);
    if (!category.equals("refresh")) {
      throw new BusinessException(BAD_REQUEST);
    }

    // DB에 저장되어 있는지 확인
    Boolean isExist = refreshRepository.existsByValue(refresh);
    if (!isExist) {
      throw new BusinessException(BAD_REQUEST);
    }
  }
}
