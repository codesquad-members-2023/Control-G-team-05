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
    private String profileImg;

    @Builder
    private ChatListPartner(Long id, String nickname, String name, String profileImg) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.profileImg = profileImg;
    }

    public void hideInfo() {
        this.name = null;
        this.profileImg = null;
    }
}
