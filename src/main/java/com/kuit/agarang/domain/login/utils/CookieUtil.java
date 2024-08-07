package com.kuit.agarang.domain.login.utils;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CookieUtil {

  public Cookie createCookie(String key, String value) {

    Cookie cookie = new Cookie(key, value);
    cookie.setMaxAge(24 * 60 * 60);
//    cookie.setSecure(true); https 환경에서만 쿠키 전송
    cookie.setPath("/");
    cookie.setHttpOnly(true);

    return cookie;
  }
}
