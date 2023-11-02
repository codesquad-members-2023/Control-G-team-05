package com.codesquad.controlG.domain.chat.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatInfoResponse {

    private ChatInfoPartner partner;

    private List<ChatInfoMessages> messages;

    @Builder
    private ChatInfoResponse(ChatInfoPartner partner, List<ChatInfoMessages> messages) {
        this.partner = partner;
        this.messages = messages;
    }

    public static ChatInfoResponse of(ChatInfoPartner chatInfoPartner, List<ChatInfoMessages> chatMessages) {
        return ChatInfoResponse.builder()
                .partner(chatInfoPartner)
                .messages(chatMessages)
                .build();
    }
}
