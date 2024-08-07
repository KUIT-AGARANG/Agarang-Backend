package com.kuit.agarang.global.s3.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum ContentType {

  JPG("image/jpeg", "jpg", "images/"),
  JPEG("image/jpeg", "jpeg", "images/"),
  PNG("image/png", "png", "images/"),
  MP3("audio/mpeg", "mp3", "audio/");

  private final String mimeType;
  private final String extension;
  private final String path;
  private static final Map<String, ContentType> allowedExtensions;

  static {
    allowedExtensions = new HashMap<>();
    for (ContentType c : ContentType.values()) {
      allowedExtensions.put(c.getExtension(), c);
    }
  }

  public static Optional<ContentType> of(String extension) {
    return Optional.ofNullable(allowedExtensions.get(extension));
  }
}
