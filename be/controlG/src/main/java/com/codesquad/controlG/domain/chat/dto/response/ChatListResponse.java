package com.codesquad.controlG.domain.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatListResponse {

    private Long chatRoomId;
    private ChatListPartner partner;
    private ChatListMessages messages;

    @Builder
    private ChatListResponse(Long chatRoomId, ChatListPartner partner, ChatListMessages messages) {
        this.chatRoomId = chatRoomId;
        this.partner = partner;
        this.messages = messages;
    }

    public void assignNewMessageCount(Long newMessageCount) {
        this.messages.assignNewMessageCount(newMessageCount);
    }
}
