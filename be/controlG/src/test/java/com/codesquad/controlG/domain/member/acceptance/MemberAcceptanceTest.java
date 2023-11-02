package com.codesquad.controlG.domain.member.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.codesquad.controlG.annotation.AcceptanceTestSupport;
import com.codesquad.controlG.domain.like.entity.Like;
import com.codesquad.controlG.domain.member.dto.LikedMemberResponse;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.fixture.FixtureFactory;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("인수 테스트 - 회원")
public class MemberAcceptanceTest extends AcceptanceTestSupport {

    @DisplayName("회원 프로필 조회에 성공한다.")
    @Test
    void getProfile_success() {
        // given
        Member joy = memberRepository.save(FixtureFactory.createMemberJoy());
        Member wiz = memberRepository.save(FixtureFactory.createMemberWiz());
        likeRepository.save(Like.builder()
                        .liked(joy)
                        .liker(wiz)
                        .build());

        // when
        var response = getProfile(joy.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo(joy.getName());
        assertThat(response.jsonPath().getString("nickname")).isEqualTo(joy.getNickname());
        assertThat(response.jsonPath().getInt("likeCount")).isEqualTo(1);
    }

    private ExtractableResponse<Response> getProfile(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .when()
                .get("/api/members")
                .then().log().all()
                .extract();
    }

    @DisplayName("like한 회원 프로필 목록 조회에 성공한다.")
    @Test
    void getLikedProfiles_success() {
        // given
        Member joy = memberRepository.save(FixtureFactory.createMemberJoy());
        Member wiz = memberRepository.save(FixtureFactory.createMemberWiz());
        likeRepository.save(Like.builder()
                .liker(joy)
                .liked(wiz)
                .build());

        // when
        var response = getLikedProfiles(joy.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("", LikedMemberResponse.class).size()).isEqualTo(1);
        assertThat(response.jsonPath().getList("", LikedMemberResponse.class).get(0).getName()).isNull();
        assertThat(response.jsonPath().getList("", LikedMemberResponse.class).get(0).getNickname()).isEqualTo("wiz");
    }

    private ExtractableResponse<Response> getLikedProfiles(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .queryParam("selected", "like")
                .when()
                .get("/api/members/profiles")
                .then().log().all()
                .extract();
    }

    @DisplayName("matched된 회원 프로필 목록 조회에 성공한다.")
    @Test
    void getMatchedProfiles_success() {
        // given
        Member joy = memberRepository.save(FixtureFactory.createMemberJoy());
        Member wiz = memberRepository.save(FixtureFactory.createMemberWiz());
        likeRepository.save(Like.builder()
                .liker(joy)
                .liked(wiz)
                .build());
        likeRepository.save(Like.builder()
                .liker(wiz)
                .liked(joy)
                .build());

        // when
        var response = getMatchedProfiles(joy.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("", LikedMemberResponse.class).size()).isEqualTo(1);
        assertThat(response.jsonPath().getList("", LikedMemberResponse.class).get(0).getName()).isEqualTo("정지혜");
        assertThat(response.jsonPath().getList("", LikedMemberResponse.class).get(0).getNickname()).isEqualTo("wiz");
    }

    private ExtractableResponse<Response> getMatchedProfiles(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .queryParam("selected", "matched")
                .when()
                .get("/api/members/profiles")
                .then().log().all()
                .extract();
    }

    @DisplayName("프로필 수정에 성공한다.")
    @Test
    void updateProfile_success() throws IOException {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberJoy());

        // when
        var response = updateProfile(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> updateProfile(Long memberId) throws IOException {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .multiPart("image", File.createTempFile("create", ".jpeg"), MediaType.IMAGE_JPEG_VALUE)
                .multiPart("nickname", "joy2")
                .when()
                .put("/api/members")
                .then().log().all()
                .extract();
    }

    @DisplayName("닉네임을 입력하지 않으면 프로필 수정에 실패한다.")
    @Test
    void updateProfile_exception() throws IOException {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberJoy());

        // when
        var response = updateProfile_noNickname(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.jsonPath().getString("message")).isEqualTo("닉네임은 비어있을 수 없습니다.");
    }

    private ExtractableResponse<Response> updateProfile_noNickname(Long memberId) throws IOException {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .multiPart("image", File.createTempFile("create", ".jpeg"), MediaType.IMAGE_JPEG_VALUE)
                .when()
                .put("/api/members")
                .then().log().all()
                .extract();
    }

    @DisplayName("좋아요에 성공한다.")
    @Test
    void like_success() {
        // given
        Member joy = memberRepository.save(FixtureFactory.createMemberJoy());
        Member wiz = memberRepository.save(FixtureFactory.createMemberWiz());

        // when
        var response = like(joy.getId(), wiz.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> like(Long memberId, Long likedId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .pathParam("likeId", likedId)
                .when()
                .post("/api/members/{likeId}/likes")
                .then().log().all()
                .extract();
    }

    @DisplayName("차단에 성공한다.")
    @Test
    void block_success() {
        // given
        Member joy = memberRepository.save(FixtureFactory.createMemberJoy());
        Member wiz = memberRepository.save(FixtureFactory.createMemberWiz());

        // when
        var response = block(joy.getId(), wiz.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> block(Long memberId, Long blockId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .pathParam("blockId", blockId)
                .when()
                .post("/api/members/{blockId}/blocks")
                .then().log().all()
                .extract();
    }
}
