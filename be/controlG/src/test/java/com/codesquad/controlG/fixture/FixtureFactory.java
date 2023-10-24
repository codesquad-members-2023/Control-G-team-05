package com.codesquad.controlG.fixture;

import com.codesquad.controlG.domain.auth.dto.request.AuthReissueTokenRequest;
import com.codesquad.controlG.domain.auth.dto.request.AuthSignUpRequest;
import com.codesquad.controlG.domain.group.dto.GroupCreateRequest;
import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.member.entity.Member;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class FixtureFactory {
  
    public static Group createGroup(String name) {
        return Group.builder()
                .name(name)
                .build();
    }

    public static GroupCreateRequest createGroupCreateRequest() {
        return GroupCreateRequest.builder()
                .name("codeSquad")
                .build();
    }

    public static Member createMemberWiz() {
        return Member.builder()
                .name("정지혜")
                .nickname("wiz")
                .email("wiz@naver.com")
                .gender("female")
                .birth(LocalDate.of(1996, 7, 30))
                .introduction("안녕하세용")
                .build();
    }

    public static AuthSignUpRequest createSignUpRequest(MultipartFile mockMultipartFile, String testNickname) {
        return AuthSignUpRequest.builder()
                .profileImage(mockMultipartFile)
                .nickname(testNickname)
                .build();
    }

    public static AuthReissueTokenRequest createReissueTokenRequest(String refreshToken) {
        return new AuthReissueTokenRequest(refreshToken);
    }

    public static Member createMemberJoy() {
        return Member.builder()
                .name("조희주")
                .nickname("joy")
                .email("joy@naver.com")
                .gender("female")
                .birth(LocalDate.of(1998, 10, 27))
                .introduction("안녕하세용")
                .build();
    }
}

