package com.codesquad.controlG.domain.auth.dto.response;

import com.codesquad.controlG.domain.token.entity.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AuthReissueTokenResponse {
    private String accessToken;
    private String refreshToken;
    private Long memberId;

    public static AuthReissueTokenResponse of(Token token, String reissuedAccessToken) {
        return AuthReissueTokenResponse.builder()
                .accessToken(reissuedAccessToken)
                .refreshToken(token.getToken())
                .memberId(token.getMember().getId())
                .build();
    }
}
