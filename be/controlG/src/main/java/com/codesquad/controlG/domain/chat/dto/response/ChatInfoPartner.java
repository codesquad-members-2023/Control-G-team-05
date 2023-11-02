package com.codesquad.controlG.domain.chat.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatInfoPartner {

    private String groupName;
    private Long id;
    private String nickname;
    private String name;
    private String gender;
    private Boolean isLiked;

    @Builder
    private ChatInfoPartner(String groupName, Long id, String nickname, String name, String gender, boolean isLiked) {
        this.groupName = groupName;
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.gender = gender;
        this.isLiked = isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public void hideName() {
        this.name = null;
    }
}
