package com.codesquad.controlG.domain.member.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    private String email;

    private String gender;

    private String profileImg;

    private LocalDate birth;

    private String introduction;

    @Builder
    private Member(Long id, String name, String nickname, String email, String gender, String profileImg,
                   LocalDate birth,
                   String introduction) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.profileImg = profileImg;
        this.birth = birth;
        this.introduction = introduction;
    }
}
