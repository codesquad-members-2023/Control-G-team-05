package com.codesquad.controlG.domain.chat.service;

import com.codesquad.controlG.domain.chat.redis.RedisChatMember;
import com.codesquad.controlG.domain.chat.repository.RedisChatMemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RedisChatMemberService {

    private final RedisChatMemberRepository redisChatMemberRepository;

    @Transactional
    public void connectChatRoom(Long chatRoomId, Long memberId, String sessionId) {
        RedisChatMember redisChatMember = RedisChatMember.of(chatRoomId, memberId, sessionId);
        redisChatMemberRepository.save(redisChatMember);
    }

    @Transactional
    public void deleteChatMember(String sessionId) {
        Optional<RedisChatMember> redisChatMember = redisChatMemberRepository.findBySessionId(sessionId);
        redisChatMember.ifPresent(redisChatMemberRepository::delete);
    }

    public int getRedisMemberSize(Long chatRoomId) {
        return redisChatMemberRepository.countByChatRoomId(chatRoomId);
    }
}
