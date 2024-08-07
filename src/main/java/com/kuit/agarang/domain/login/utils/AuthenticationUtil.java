package com.kuit.agarang.domain.login.utils;

import com.kuit.agarang.domain.login.model.dto.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class AuthenticationUtil {

  public String getProviderId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
    return customUserDetails.getProviderId();
  }

  public String getRole() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
    GrantedAuthority auth = iterator.next();
    return auth.getAuthority();
  }

  public Long getMemberId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
    return customUserDetails.getMemberId();
  }

}
