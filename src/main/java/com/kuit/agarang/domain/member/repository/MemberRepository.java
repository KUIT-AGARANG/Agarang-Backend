package com.kuit.agarang.domain.member.repository;

import com.kuit.agarang.domain.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByProviderId(String providerId);
}
