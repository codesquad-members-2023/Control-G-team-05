package com.codesquad.controlG.domain.member.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequest {

    @NotBlank(message = "닉네임은 비어있을 수 없습니다.")
    private String nickname;
    private String introduction;
}
