package com.codesquad.controlG.domain.auth.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthReissueTokenRequest {
    private String refreshToken;
}
