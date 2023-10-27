package com.codesquad.controlG.domain.chat.dto.response;

import com.codesquad.controlG.domain.chat.entity.ChatMessage;
import com.codesquad.controlG.domain.chat.entity.ChatRoom;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatSendMessageResponse {

    private Long chatRoomId;
    private MessagesResponse messages;

    @Builder
    private ChatSendMessageResponse(Long chatRoomId, MessagesResponse messages) {
        this.chatRoomId = chatRoomId;
        this.messages = messages;
    }


    public static ChatSendMessageResponse of(ChatMessage chatMessage, ChatRoom chatRoom) {
        return ChatSendMessageResponse.builder()
                .messages(MessagesResponse.of(chatMessage))
                .chatRoomId(chatRoom.getId())
                .build();
    }
}
