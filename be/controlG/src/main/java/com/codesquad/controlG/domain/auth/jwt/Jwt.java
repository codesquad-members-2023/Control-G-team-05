package com.codesquad.controlG.domain.auth.jwt;

import lombok.Getter;

@Getter
public class Jwt {

    private String accessToken;
    private String refreshToken;
    private String signUpToken;

    public Jwt(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Jwt(String signUpToken) {
        this.signUpToken = signUpToken;
    }
}
