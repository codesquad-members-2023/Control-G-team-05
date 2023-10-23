package com.codesquad.controlG.domain.group.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class GroupAddMineRequest {

    private final MultipartFile image;

    @Builder
    private GroupAddMineRequest(MultipartFile image) {
        this.image = image;
    }
}
