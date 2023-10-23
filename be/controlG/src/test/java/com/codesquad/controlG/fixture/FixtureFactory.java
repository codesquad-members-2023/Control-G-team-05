package com.codesquad.controlG.fixture;

import com.codesquad.controlG.domain.group.dto.GroupCreateRequest;
import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.member.entity.Member;
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
}
