package com.codesquad.controlG.domain.chat.dto.response;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatListMessages {

    private String lastMessage;
    private Timestamp lastSendTime;
    private Long newMessageCount;

    @Builder
    private ChatListMessages(String lastMessage, Timestamp lastSendTime, Long newMessageCount) {
        this.lastMessage = lastMessage;
        this.lastSendTime = lastSendTime;
        this.newMessageCount = newMessageCount;
    }

    public void assignNewMessageCount(Long newMessageCount) {
        this.newMessageCount = newMessageCount;
    }
}
