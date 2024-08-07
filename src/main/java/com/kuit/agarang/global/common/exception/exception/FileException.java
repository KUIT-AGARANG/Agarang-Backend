package com.kuit.agarang.global.common.exception.exception;

import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import lombok.Getter;

@Getter
public class FileException extends BusinessException {
  public FileException(BaseResponseStatus baseResponseStatus) {
    super(baseResponseStatus);
  }
}
