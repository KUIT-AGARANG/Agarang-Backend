package com.kuit.agarang.global.common.model.dto;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class HeaderInfo {

  private static final String QUERY_PREFIX = "?";

  private String host;
  private String cookie;
  private String method;
  private String uri;
  private String queryString;

  @Builder
  public HeaderInfo(String host, String cookie, String method, String uri, String queryString) {
    this.host = host;
    this.cookie = cookie;
    this.method = method;
    this.uri = uri;
    this.queryString = queryString;
  }

  public static HeaderInfo of(HttpServletRequest request) {
    return HeaderInfo.builder()
      .host(request.getHeader("host"))
      .cookie(request.getHeader("cookie"))
      .method(request.getMethod())
      .uri(request.getRequestURI())
      .queryString(request.getQueryString() == null ? null : QUERY_PREFIX + request.getQueryString())
      .build();
  }
}
