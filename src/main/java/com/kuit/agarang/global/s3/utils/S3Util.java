package com.kuit.agarang.global.s3.utils;

import com.kuit.agarang.global.common.exception.exception.FileException;
import com.kuit.agarang.global.common.model.dto.BaseResponseStatus;
import com.kuit.agarang.global.s3.model.dto.S3File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Uri;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Util {

  private final S3FileUtil s3FileUtil;
  private final S3Client s3Client;
  @Value("${aws.s3.bucket}")
  private String bucket;

  public S3File upload(MultipartFile file) throws Exception {
    log.info("S3 파일 업로드가 시작되었습니다. [{} of {}]", file.getOriginalFilename(), file.getContentType());
    S3File s3File = s3FileUtil.uploadTempFile(file);
    return upload(s3File);
  }

  private S3File upload(S3File s3File) {
    try {
      PutObjectRequest request = PutObjectRequest.builder()
        .bucket(bucket)
        .key(s3File.getFilename())
        .contentType(s3File.getContentType().getMimeType())
        .contentLength(s3File.getContentLength())
        .build();

      s3Client.putObject(request, RequestBody.fromBytes(s3File.getBytes()));
      log.info("S3 파일 업로드가 완료되었습니다. [{}]", s3File.getFilename());
      return s3File.putObjectUrl(getUrl(s3File.getFilename()));
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileException(BaseResponseStatus.FAIL_S3_UPLOAD);
    } finally {
      s3FileUtil.deleteTempFile(s3File);
    }
  }

  public void delete(String objectUrl) {
    S3Uri s3Uri = s3Client.utilities().parseUri(URI.create(objectUrl));
    String filename = s3Uri.key()
      .orElseThrow(() -> new FileException(BaseResponseStatus.NOT_FOUND_S3_FILE));
    deleteObject(filename);
  }

  private void deleteObject(String filename) {
    try {
      DeleteObjectRequest request = DeleteObjectRequest.builder()
        .bucket(bucket)
        .key(filename)
        .build();
      s3Client.deleteObject(request);
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileException(BaseResponseStatus.FAIL_S3_UPLOAD);
    }
  }

  private String getUrl(String filename) {
    try {
      GetUrlRequest request = GetUrlRequest.builder()
        .bucket(bucket)
        .key(filename)
        .build();
      return s3Client.utilities().getUrl(request).toString();
    } catch (Exception e) {
      e.printStackTrace();
      throw new FileException(BaseResponseStatus.FAIL_S3_UPLOAD);
    }
  }
}
