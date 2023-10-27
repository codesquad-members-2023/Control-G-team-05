package com.codesquad.controlG.domain.chat.redis;

import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "RedisChatQueue")
public class RedisChatQueue {

    @Id
    private String id;

    @Indexed
    private Long groupId;

    @Indexed
    private Long memberId;

    @Builder
    private RedisChatQueue(String id, Long groupId, Long memberId) {
        this.id = id;
        this.groupId = groupId;
        this.memberId = memberId;
    }

    public static RedisChatQueue of(Long groupId, Long memberId) {
        return RedisChatQueue.builder()
                .id(groupId + "_" + memberId)
                .groupId(groupId)
                .memberId(memberId)
                .build();
    }
}
