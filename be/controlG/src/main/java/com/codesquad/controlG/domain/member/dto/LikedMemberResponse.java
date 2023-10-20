package com.codesquad.controlG.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LikedMemberResponse {

    @JsonInclude(Include.NON_NULL)
    private String name;
    private String nickname;
    private String profileImg;
    private String introduction;

    @Builder
    private LikedMemberResponse(String name, String nickname, String profileImg, String introduction) {
        this.name = name;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.introduction = introduction;
    }
}
