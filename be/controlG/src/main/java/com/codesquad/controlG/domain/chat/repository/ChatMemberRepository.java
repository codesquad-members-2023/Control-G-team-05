package com.codesquad.controlG.domain.chat.repository;

import com.codesquad.controlG.domain.chat.entity.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
}
