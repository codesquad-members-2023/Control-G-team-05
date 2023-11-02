package com.codesquad.controlG.domain.member.dto;

import com.codesquad.controlG.domain.member.entity.Member;
import java.time.LocalDate;
import java.time.Period;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponse {

    private String name;
    private String nickname;
    private String profileImg;
    private String gender;
    private int age;
    private Long likeCount;
    private String introduction;

    @Builder
    private MemberResponse(String name, String nickname, String profileImg, String gender, int age,
                          Long likeCount,
                          String introduction) {
        this.name = name;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.gender = gender;
        this.age = age;
        this.likeCount = likeCount;
        this.introduction = introduction;
    }

    public static MemberResponse of(Member member, Long likeCount) {
        return MemberResponse.builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .profileImg(member.getProfileImg())
                .gender(member.getGender())
                .age(calculateAge(member.getBirth()))
                .likeCount(likeCount)
                .introduction(member.getIntroduction())
                .build();
    }

    private static int calculateAge(LocalDate birth) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birth, now);
        return period.getYears();
    }
}
