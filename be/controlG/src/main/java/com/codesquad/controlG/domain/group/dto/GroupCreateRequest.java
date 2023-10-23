package com.codesquad.controlG.domain.group.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class GroupCreateRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private final String name;

    private final MultipartFile image;

    @Builder
    private GroupCreateRequest(String name, MultipartFile image) {
        this.name = name;
        this.image = image;
    }
}
