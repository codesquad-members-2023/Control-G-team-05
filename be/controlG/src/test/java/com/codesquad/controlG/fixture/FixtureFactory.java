package com.codesquad.controlG.fixture;

import com.codesquad.controlG.domain.group.dto.GroupCreateRequest;
import com.codesquad.controlG.domain.group.entity.Group;

public class FixtureFactory {
    public static Group createGroup() {
        return Group.builder()
                .name("codeSquad")
                .build();
    }

    public static GroupCreateRequest createGroupCreateRequest() {
        return GroupCreateRequest.builder()
                .name("codeSquad")
                .build();
    }
}
