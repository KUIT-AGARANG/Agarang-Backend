package com.kuit.agarang.global.s3.utils;

import com.kuit.agarang.global.common.exception.exception.BusinessException;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import com.kuit.agarang.global.s3.model.dto.S3File;
import com.kuit.agarang.global.s3.model.enums.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class S3FileUtil {

  @Value("${aws.s3.upload.tempPath}")
  private String tempPath;

  private S3File convert(MultipartFile file) throws Exception {
    ContentType contentType = getContentType(file)
      .orElseThrow(() -> new BusinessException(BaseResponseStatus.INVALID_FILE_EXTENSION));
    return S3File.builder()
      .filename(createCleanedFilename(file, contentType))
      .contentType(contentType)
      .contentLength(file.getSize())
      .bytes(file.getBytes())
      .build();
  }

  public S3File uploadTempFile(MultipartFile file) throws Exception {
    S3File s3File = convert(file);
    uploadTempFile(s3File);
    return s3File;
  }

  private void uploadTempFile(S3File s3File) throws IOException {
    File directory = new File(tempPath + s3File.getContentType().getPath());
    if (!directory.exists()) directory.mkdirs();

    File file = new File(tempPath, s3File.getFilename());
    if (file.createNewFile()) {
      try (FileOutputStream fos = new FileOutputStream(file)) {
        fos.write(s3File.getBytes());
      }
    }
  }

  public void deleteTempFile(S3File s3File) {
    File file = new File(tempPath + s3File.getFilename());
    if (file.exists()) {
      if (file.delete()) {
        log.info("임시 업로드 파일이 성공적으로 삭제되었습니다. [{}]", file.getPath());
        return;
      }
      log.info("임시 업로드 파일 삭제를 실패했습니다. [{}]", file.getPath());
    }
  }

  public byte[] getTempFile(S3File s3File) throws IOException {
    File file = new File(tempPath, s3File.getFilename());
    if (!file.exists()) {
      throw new IOException("임시 파일을 찾을 수 없습니다. : " + s3File.getFilename());
    }
    try (FileInputStream fis = new FileInputStream(file)) {
      return fis.readAllBytes();
    }
  }

  private Optional<ContentType> getContentType(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    return ContentType.of(extension);
  }

  private String createCleanedFilename(MultipartFile file, ContentType contentType) {
    // 모든 (/), (\), ( ) -> (_) 로 대체
    String cleanedFilename = file.getOriginalFilename().replaceAll("[/\\\\\\s]+", "_");
    return contentType.getPath() + UUID.randomUUID() + "_" + cleanedFilename;
  }
}
