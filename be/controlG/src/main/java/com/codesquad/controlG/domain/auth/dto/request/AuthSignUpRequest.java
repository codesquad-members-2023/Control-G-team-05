package com.codesquad.controlG.domain.auth.dto.request;

import com.codesquad.controlG.domain.member.entity.Member;
import java.time.LocalDate;
import java.util.Map;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class AuthSignUpRequest {

    private MultipartFile profileImage;
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @Builder
    private AuthSignUpRequest(MultipartFile profileImage, String nickname) {
        this.profileImage = profileImage;
        this.nickname = nickname;
    }

    public static Member of(AuthSignUpRequest authSignUpRequest, String imageUrl, Map<String, Object> map) {
        return Member.builder()
                .profileImg(imageUrl)
                .birth(LocalDate.parse((CharSequence) map.get("birthday")))
                .email((String) map.get("email"))
                .name((String) map.get("name"))
                .nickname(authSignUpRequest.getNickname())
                .gender((String) map.get("gender"))
                .build();
    }
}
