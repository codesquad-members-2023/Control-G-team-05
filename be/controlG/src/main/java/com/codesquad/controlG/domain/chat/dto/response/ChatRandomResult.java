package com.codesquad.controlG.domain.chat.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRandomResult {

    private final Long chatRoomId;
    private final Long partnerId;

    @Builder
    private ChatRandomResult(Long chatRoomId, Long partnerId) {
        this.chatRoomId = chatRoomId;
        this.partnerId = partnerId;
    }

    public static ChatRandomResult of(Long chatRoomId, Long partnerId) {
        return ChatRandomResult.builder()
                .chatRoomId(chatRoomId)
                .partnerId(partnerId)
                .build();
    }
}
