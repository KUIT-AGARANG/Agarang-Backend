package com.kuit.agarang.global.s3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

  @Value("${aws.credentials.accessKey}")
  private String accessKey;
  @Value("${aws.credentials.secretKey}")
  private String secretKey;

  @Bean
  public S3Client amazonS3Client() {
    AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(accessKey, secretKey);
    return S3Client.builder()
      .region(Region.AP_NORTHEAST_2)
      .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
      .build();
  }
}
