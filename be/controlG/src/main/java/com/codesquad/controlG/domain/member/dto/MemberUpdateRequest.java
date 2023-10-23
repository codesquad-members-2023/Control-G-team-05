package com.codesquad.controlG.domain.member.dto;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class MemberUpdateRequest {

    @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
    private String nickname;
    private MultipartFile profileImg;
    private String introduction;

    @Builder
    private MemberUpdateRequest(String nickname, MultipartFile profileImg, String introduction) {
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.introduction = introduction;
    }
}
