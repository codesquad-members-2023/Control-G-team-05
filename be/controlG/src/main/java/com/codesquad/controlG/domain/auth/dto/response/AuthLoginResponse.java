package com.codesquad.controlG.domain.auth.dto.response;

import com.codesquad.controlG.domain.auth.jwt.Jwt;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthLoginResponse {

    private String accessToken;
    private String refreshToken;
    private Long memberId;

    @Builder
    private AuthLoginResponse(String accessToken, String refreshToken, Long memberId) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }

    public static AuthLoginResponse of(Jwt jwt, Long memberId) {
        return AuthLoginResponse.builder()
                .accessToken(jwt.getAccessToken())
                .refreshToken(jwt.getRefreshToken())
                .memberId(memberId)
                .build();
    }
}
