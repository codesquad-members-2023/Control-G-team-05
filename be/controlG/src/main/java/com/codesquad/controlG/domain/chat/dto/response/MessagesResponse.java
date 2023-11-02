package com.codesquad.controlG.domain.chat.dto.response;

import com.codesquad.controlG.domain.chat.entity.ChatMessage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MessagesResponse {

    private String message;
    private Long senderId;

    @Builder
    private MessagesResponse(String message, Long senderId) {
        this.message = message;
        this.senderId = senderId;
    }

    public static MessagesResponse of(ChatMessage chatMessage) {
        return MessagesResponse.builder()
                .message(chatMessage.getMessage())
                .senderId(chatMessage.getSender().getId())
                .build();
    }
}
