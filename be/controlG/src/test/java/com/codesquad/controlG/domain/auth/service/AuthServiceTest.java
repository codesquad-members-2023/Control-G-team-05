package com.codesquad.controlG.domain.auth.service;

import com.codesquad.controlG.annotation.ServiceTest;
import com.codesquad.controlG.annotation.ServiceTestSupport;
import com.codesquad.controlG.domain.auth.Oauth.OauthProvider;
import com.codesquad.controlG.domain.auth.Oauth.dto.OauthTokenResponse;
import com.codesquad.controlG.domain.auth.Oauth.dto.UserProfile;
import com.codesquad.controlG.domain.auth.dto.response.AuthLoginResponse;
import com.codesquad.controlG.domain.auth.jwt.Jwt;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.exception.errorcode.LoginMemberNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ServiceTest
class AuthServiceTest extends ServiceTestSupport {

    @Test
    @DisplayName("로그인 할 이메일이 존재 하는 경우 로그인에 성공한다.")
    void login() {
        // given
        mockingOAuth();
        BDDMockito.given(memberRepository.findByEmail(anyString())).willReturn(Optional.ofNullable(Member.builder()
                .id(1L)
                .email(TEST_EMAIL)
                .build()));
        BDDMockito.given(jwtProvider.createTokens(any())).willReturn(new Jwt("access", "refresh"));
        BDDMockito.given(inMemoryProviderRepository.findByProviderName(anyString())).willReturn(new OauthProvider("dd", "dd", "dd", "dd", "dd"));
        // when
        AuthLoginResponse response = authService.login("naver", "code");
        // then
        Member member = memberRepository.findByEmail(TEST_EMAIL).orElseThrow();
        assertAll(
                () -> assertThat(response.getMemberId()).isEqualTo(member.getId())
        );
    }

    @Test
    @DisplayName("로그인 할 이메일이 존재하지 않는 경우 로그인에 실패하고 예외발생 및 회원가입 토큰 발급")
    void loginFailed() {
        // given
        mockingOAuth();
        BDDMockito.given(memberRepository.findByEmail(anyString())).willReturn(Optional.empty());
        BDDMockito.given(inMemoryProviderRepository.findByProviderName(anyString())).willReturn(new OauthProvider("dd", "dd", "dd", "dd", "dd"));
        BDDMockito.given(jwtProvider.createSignUpToken(any())).willReturn(new Jwt("test"));
        // when&then
        assertAll(
                () -> Assertions.assertThatThrownBy(() -> authService.login("naver", "code")).isInstanceOf(LoginMemberNotFoundException.class));
    }

    private void mockingOAuth() {
        BDDMockito.given(oauthRequester.getToken(anyString(), any(OauthProvider.class)))
                .willReturn(new OauthTokenResponse("accessToken", "bearer"));
        BDDMockito.given(oauthRequester.getUserProfile(anyString(), any(OauthTokenResponse.class), any(OauthProvider.class)))
                .willReturn(new UserProfile(TEST_EMAIL, TEST_NAME, TEST_GENDER, TEST_BIRTH_YEAR, TEST_BIRTHDAY));
    }
}
