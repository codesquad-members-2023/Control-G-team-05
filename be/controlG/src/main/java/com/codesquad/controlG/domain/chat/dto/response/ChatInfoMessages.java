package com.codesquad.controlG.domain.chat.dto.response;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatInfoMessages {

    private Long id;
    private Long senderId;
    private String message;
    private Timestamp sentAt;
    private Boolean isRead;

    @Builder
    private ChatInfoMessages(Long id, Long senderId, String message, Timestamp sentAt, boolean isRead) {
        this.id = id;
        this.senderId = senderId;
        this.message = message;
        this.sentAt = sentAt;
        this.isRead = isRead;
    }
}
