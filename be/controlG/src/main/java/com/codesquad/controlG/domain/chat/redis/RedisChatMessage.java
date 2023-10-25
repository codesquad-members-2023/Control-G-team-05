package com.codesquad.controlG.domain.chat.redis;

import java.sql.Timestamp;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "RedisChatMessage")
public class RedisChatMessage {

    @Id
    private Long id;

    @Indexed
    private Long chatRoomId;

    @Indexed
    private Long senderId;

    private String message;

    private Timestamp sentAt;

    private boolean isRead;

    @Builder
    private RedisChatMessage(Long chatRoomId, Long senderId, String message, Timestamp sentAt, boolean isRead) {
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.message = message;
        this.sentAt = sentAt;
        this.isRead = isRead;
    }
}
