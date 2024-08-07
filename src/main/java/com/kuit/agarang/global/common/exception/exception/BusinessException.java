package com.kuit.agarang.global.common.exception.exception;

import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private final BaseResponseStatus baseResponseStatus;

  public BusinessException(BaseResponseStatus baseResponseStatus) {
    this.baseResponseStatus = baseResponseStatus;
  }
}
