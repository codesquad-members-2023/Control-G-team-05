package com.codesquad.controlG.domain.chat_member.repository;

import com.codesquad.controlG.domain.chat_member.entity.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
}
