package com.codesquad.controlG.domain.member.dto;

import com.codesquad.controlG.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikedMemberResponse {

    private Long id;
    @JsonInclude(Include.NON_NULL)
    private String name;
    private String nickname;
    private String profileImg;
    private String introduction;

    @Builder
    private LikedMemberResponse(Long id, String name, String nickname, String profileImg, String introduction) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.introduction = introduction;
    }

    public static LikedMemberResponse fromLike(Member member) {
        return LikedMemberResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .introduction(member.getIntroduction())
                .build();
    }

    public static LikedMemberResponse fromMatched(Member member) {
        return LikedMemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .profileImg(member.getProfileImg())
                .introduction(member.getIntroduction())
                .build();
    }
}
