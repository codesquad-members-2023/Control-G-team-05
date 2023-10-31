package com.codesquad.controlG.domain.chat.repository;

import com.codesquad.controlG.domain.chat.entity.ChatMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {

    Optional<ChatMember> findByChatRoomIdAndMemberId(Long chatRoomId, Long memberId);

    int countByChatRoomIdAndIsExit(Long chatRoomId, boolean isExit);

    void deleteByChatRoomId(Long chatRoomId);
}
