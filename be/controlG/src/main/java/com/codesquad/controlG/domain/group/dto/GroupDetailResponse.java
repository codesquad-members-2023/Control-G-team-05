package com.codesquad.controlG.domain.group.dto;

import com.codesquad.controlG.domain.group.entity.Group;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GroupDetailResponse {

    private final Long id;
    private final String name;
    private final String img;
    private final Long headCount;

    @Builder
    private GroupDetailResponse(Long id, String name, String img, Long headCount) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.headCount = headCount;
    }

    public static GroupDetailResponse of(Group group, Long headCount) {
        return GroupDetailResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .img(group.getImg())
                .headCount(headCount)
                .build();
    }
}
