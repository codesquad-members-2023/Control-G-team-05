package com.codesquad.controlG.domain.member.repository;

import com.codesquad.controlG.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
