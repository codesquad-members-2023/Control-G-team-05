package com.codesquad.controlG.domain.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatListPartner {

    private Long id;
    private String nickname;
    private String name;

    @Builder
    private ChatListPartner(Long id, String nickname, String name) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
    }

    public void hideName() {
        this.name = null;
    }
}
