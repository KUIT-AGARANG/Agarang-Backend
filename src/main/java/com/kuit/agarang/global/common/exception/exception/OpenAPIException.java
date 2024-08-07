package com.kuit.agarang.global.common.exception.exception;

import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import lombok.Getter;

@Getter
public class OpenAPIException extends BusinessException {
  public OpenAPIException(BaseResponseStatus baseResponseStatus) {
    super(baseResponseStatus);
  }
}
