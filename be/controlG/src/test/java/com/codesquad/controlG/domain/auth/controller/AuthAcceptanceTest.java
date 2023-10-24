package com.codesquad.controlG.domain.auth.controller;


import com.codesquad.controlG.annotation.AcceptanceTestSupport;
import com.codesquad.controlG.domain.auth.Oauth.OauthProvider;
import com.codesquad.controlG.domain.auth.Oauth.dto.OauthTokenResponse;
import com.codesquad.controlG.domain.auth.Oauth.dto.UserProfile;
import com.codesquad.controlG.domain.auth.dto.request.AuthSignUpRequest;
import com.codesquad.controlG.domain.auth.dto.response.AuthLoginResponse;
import com.codesquad.controlG.domain.auth.jwt.Jwt;
import com.codesquad.controlG.exception.CustomRuntimeException;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.codesquad.controlG.fixture.FixtureFactory.createReissueTokenRequest;
import static com.codesquad.controlG.fixture.FixtureFactory.createSignUpRequest;
import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.*;


class AuthAcceptanceTest extends AcceptanceTestSupport {

    private void mockingOAuth() {
        given(oauthRequester.getToken(anyString(), any(OauthProvider.class)))
                .willReturn(new OauthTokenResponse("accessToken", "bearer"));
        given(oauthRequester.getUserProfile(anyString(), any(OauthTokenResponse.class), any(OauthProvider.class)))
                .willReturn(new UserProfile(TEST_EMAIL, TEST_NAME, TEST_GENDER, TEST_BIRTH_YEAR, TEST_BIRTHDAY));
    }

    private MockMultipartFile createMockMultipartFile(String fileName, String extension) {
        return new MockMultipartFile(
                "test-image",
                fileName,
                extension,
                "imageBytes".getBytes(StandardCharsets.UTF_8)
        );
    }

    private Map<String, Object> createSignUpMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("email", TEST_EMAIL);
        map.put("gender", TEST_GENDER);
        map.put("birthday", TEST_BIRTH_YEAR + "-" + TEST_BIRTHDAY);
        map.put("name", TEST_NAME);
        return map;
    }

    @Test
    @DisplayName("회원가입이 되어 있으면 로그인에 성공한다.")
    void signIn() {
        // given
        var mockMultipartFile = createMockMultipartFile("test.png", MediaType.IMAGE_PNG_VALUE);
        mockingOAuth();
        AuthSignUpRequest signUpRequest = createSignUpRequest(mockMultipartFile, TEST_NICKNAME);
        authService.signUp(signUpRequest, createSignUpMap());

        var request = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("code", "code")
                .queryParam("state", "state");
        // when
        var response = login(request);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getString("accessToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("refreshToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("memberId")).isNotNull()
        );
    }

    @Test
    @DisplayName("회원가입이 되어 있지 않으면 로그인에 실패하고 회원가입 토큰을 담은 응답을 반환한다.")
    void signIn_failed() {
        // given
        mockingOAuth();

        var request = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .queryParam("code", "code")
                .queryParam("state", "state");

        // when
        var response = login(request);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(422),
                () -> assertThat(response.jsonPath().getString("message.signupToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("message.error")).isNotNull()
        );
    }

    private ExtractableResponse<Response> login(RequestSpecification request) {
        return request
                .post("/api/login/oauth/naver")
                .then().log().all()
                .extract();
    }


    @Test
    @DisplayName("회원가입에 성공한다.")
    void signUp() throws IOException {

        // given
        Map<String, Object> signUpMap = createSignUpMap();
        Jwt signUpToken = jwtProvider.createSignUpToken(signUpMap);

        // when
        var response = RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + signUpToken.getSignUpToken())
                .multiPart("profileImage", File.createTempFile("test", ".jpeg"), MediaType.IMAGE_JPEG_VALUE)
                .multiPart("nickname", TEST_NICKNAME)
                .when()
                .post("/api/auth/signup")
                .then().log().all()
                .extract();
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getString("accessToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("refreshToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("memberId")).isNotNull()
        );
    }

    @Test
    @DisplayName("회원가입 토큰이 존재하지 않으면 회원가입을 실패한다.")
    void signUpFailed() throws IOException {

        // given&when
        var response = RestAssured
                .given().log().all()
                .multiPart("profileImage", File.createTempFile("test", ".jpeg"), MediaType.IMAGE_JPEG_VALUE)
                .multiPart("nickname", TEST_NICKNAME)
                .when()
                .post("/api/auth/signup")
                .then().log().all()
                .extract();
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(401),
                () -> assertThat(response.jsonPath().getString("message")).isEqualTo("Header에 토큰이 존재하지 않습니다.")
        );
    }

    @Test
    @DisplayName("엑세스 토큰을 블랙리스트에 등록하고 DB에서 리프레시토큰을 삭제한다.")
    void signOut() {

        // given
        var mockMultipartFile = createMockMultipartFile("test.png", MediaType.IMAGE_PNG_VALUE);
        AuthSignUpRequest signUpRequest = createSignUpRequest(mockMultipartFile, TEST_NICKNAME);
        Map<String, Object> signUpMap = createSignUpMap();
        AuthLoginResponse authLoginResponse = authService.signUp(signUpRequest, signUpMap);
        String accessToken = authLoginResponse.getAccessToken();
        String refreshToken = authLoginResponse.getRefreshToken();

        // when
        var response = RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION, JWT_TOKEN_PREFIX + accessToken)
                .when()
                .post("/api/auth/logout")
                .then().log().all()
                .extract();
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getString("message")).isNotNull(),
                () -> assertThat(redisUtil.hasKeyBlackList(accessToken)).isTrue(),
                () -> Assertions.assertThatThrownBy(() -> tokenService.findByRefreshToken(refreshToken)).isInstanceOf(
                        CustomRuntimeException.class)
        );
    }

    @AfterEach
    void resetRedis() {
        redisBlackListTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    void reissueToken() {

        // given
        var mockMultipartFile = createMockMultipartFile("test.png", MediaType.IMAGE_PNG_VALUE);
        AuthSignUpRequest signUpRequest = createSignUpRequest(mockMultipartFile, TEST_NICKNAME);
        Map<String, Object> signUpMap = createSignUpMap();
        AuthLoginResponse authLoginResponse = authService.signUp(signUpRequest, signUpMap);
        String accessToken = authLoginResponse.getAccessToken();
        String refreshToken = authLoginResponse.getRefreshToken();

        // when
        var response = RestAssured
                .given().log().all()
                .when()
                .body(createReissueTokenRequest(refreshToken))
                .contentType(APPLICATION_JSON)
                .post("api/auth/access-token")
                .then().log().all()
                .extract();

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(200),
                () -> assertThat(response.jsonPath().getString("accessToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getString("refreshToken")).isNotNull(),
                () -> assertThat(response.jsonPath().getLong("memberId")).isEqualTo(authLoginResponse.getMemberId())
        );
    }
}
