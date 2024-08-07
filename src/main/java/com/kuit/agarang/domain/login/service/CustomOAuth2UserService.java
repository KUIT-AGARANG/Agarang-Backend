package com.kuit.agarang.domain.login.service;

import com.kuit.agarang.domain.login.model.dto.*;
import com.kuit.agarang.domain.member.model.dto.MemberDTO;
import com.kuit.agarang.domain.member.model.entity.Member;
import com.kuit.agarang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

  private final MemberRepository memberRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    OAuth2User oAuth2User = super.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    OAuth2Response oAuth2Response = null;

    switch (registrationId) {
      case "naver" -> oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
      case "kakao" -> oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
      case "google" -> oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
      default -> {
        return null;
      }
    }

    String providerId = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();
    Optional<Member> optionalMember = memberRepository.findByProviderId(providerId);

    if (optionalMember.isEmpty()) {

      Member member = Member.of(providerId, oAuth2Response.getName(), oAuth2Response.getEmail(), "ROLE_USER");
      memberRepository.save(member);

      MemberDTO memberDTO = MemberDTO.of(providerId, oAuth2Response.getName(), "ROLE_USER");
      return new CustomOAuth2User(memberDTO);

    } else {

      Member existData = optionalMember.get();
      existData.changeInfo(oAuth2Response.getName(), oAuth2Response.getEmail());

      MemberDTO memberDTO = MemberDTO.of(existData.getProviderId(), oAuth2Response.getName(), existData.getRole());
      return new CustomOAuth2User(memberDTO);
    }
  }

}
