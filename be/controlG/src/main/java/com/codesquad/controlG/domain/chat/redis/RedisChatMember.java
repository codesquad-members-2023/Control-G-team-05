package com.codesquad.controlG.domain.chat.redis;

import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "RedisChatMember")
public class RedisChatMember {

    @Id
    private String id;

    @Indexed
    private Long chatRoomId;

    @Indexed
    private Long memberId;

    @Indexed
    private String sessionId;

    @Builder
    private RedisChatMember(Long chatRoomId, Long memberId, String sessionId) {
        this.chatRoomId = chatRoomId;
        this.memberId = memberId;
        this.sessionId = sessionId;
    }

    @Builder
    public static RedisChatMember of(Long chatRoomId, Long memberId, String sessionId) {
        return RedisChatMember.builder()
                .chatRoomId(chatRoomId)
                .memberId(memberId)
                .sessionId(sessionId)
                .build();
    }
}
