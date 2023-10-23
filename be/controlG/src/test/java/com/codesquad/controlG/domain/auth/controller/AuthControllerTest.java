package com.codesquad.controlG.domain.auth.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

import com.codesquad.controlG.annotation.AcceptanceTest;
import com.codesquad.controlG.domain.auth.Oauth.OauthProvider;
import com.codesquad.controlG.domain.auth.Oauth.OauthRequester;
import com.codesquad.controlG.domain.auth.Oauth.dto.OauthTokenResponse;
import com.codesquad.controlG.domain.auth.Oauth.dto.UserProfile;
import com.codesquad.controlG.domain.auth.dto.request.AuthSignUpRequest;
import com.codesquad.controlG.domain.auth.jwt.Jwt;
import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.domain.auth.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@AcceptanceTest
class AuthControllerTest {

    @MockBean
    OauthRequester oauthRequester;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;

    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String TEST_EMAIL = "test@test.com";
    public static final String TEST_GENDER = "M";
    public static final String TEST_NAME = "라이트";
    public static final String TEST_NICKNAME = "test";
    public static final String TEST_BIRTHDAY = "06-18";
    public static final String TEST_BIRTH_YEAR = "1998";


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
        AuthSignUpRequest signUpRequest = AuthSignUpRequest.builder()
                .profileImage(mockMultipartFile)
                .nickname(TEST_NICKNAME)
                .build();
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

        // given
        var mockMultipartFile = createMockMultipartFile("test.png", MediaType.IMAGE_PNG_VALUE);
        Map<String, Object> signUpMap = createSignUpMap();
        Jwt signUpToken = jwtProvider.createSignUpToken(signUpMap);

        // when
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
}