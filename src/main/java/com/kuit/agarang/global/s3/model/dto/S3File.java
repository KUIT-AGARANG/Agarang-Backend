package com.kuit.agarang.global.s3.model.dto;

import com.kuit.agarang.global.s3.model.enums.ContentType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Getter
@NoArgsConstructor
public class S3File {
  private String filename;
  private ContentType contentType;
  private Long contentLength;
  private byte[] bytes;
  private String objectUrl;

  @Builder
  public S3File(String filename, ContentType contentType, Long contentLength, byte[] bytes) {
    this.filename = filename;
    this.contentType = contentType;
    this.contentLength = contentLength;
    this.bytes = bytes;
  }

  public S3File putObjectUrl(String objectUrl) {
    this.objectUrl = objectUrl;
    return this;
  }

  public String toGPTImageUrl() {
    String base64EncodeData = Base64.getEncoder().encodeToString(this.getBytes());
    return "data:" + this.getContentType().getMimeType() + ";base64," + base64EncodeData;
  }

  public S3File cleanBytes() {
    this.bytes = null;
    return this;
  }
}
