package com.codesquad.controlG.domain.chat.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MessageRequest {

    private Long chatRoomId;
    private String message;
    private Long senderId;

    @Builder
    private MessageRequest(Long chatRoomId, String message, Long senderId) {
        this.chatRoomId = chatRoomId;
        this.message = message;
        this.senderId = senderId;
    }
}
