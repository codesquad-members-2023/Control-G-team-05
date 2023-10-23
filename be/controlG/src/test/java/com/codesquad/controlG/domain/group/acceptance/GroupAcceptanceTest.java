package com.codesquad.controlG.domain.group.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.codesquad.controlG.annotation.AcceptanceTestSupport;
import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member_group.entity.MemberGroup;
import com.codesquad.controlG.exception.errorcode.GroupException;
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

public class GroupAcceptanceTest extends AcceptanceTestSupport {

    @DisplayName("그룹을 등록한다.")
    @Test
    void createGroup_success() throws IOException {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());

        // when
        var response = createGroup(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> createGroup(Long memberId) throws IOException {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .multiPart("image", File.createTempFile("create", ".jpeg"), MediaType.IMAGE_JPEG_VALUE)
                .multiPart("name", "codeSquad")
                .when()
                .post("/api/groups")
                .then().log().all()
                .extract();
    }

    @DisplayName("사진없이 그룹을 등록한다.")
    @Test
    void createGroup_success_NoImage() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());

        // when
        var response = createGroup_NoImage(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> createGroup_NoImage(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .multiPart("name", "codeSquad")
                .when()
                .post("/api/groups")
                .then().log().all()
                .extract();
    }

    @DisplayName(" 이름이 같은 그룹이 있다면 새 그룹 추가 신청에 실패한다.")
    @Test
    void createGroup_fail_SameName() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group group = FixtureFactory.createGroup("codeSquad");
        groupRepository.save(group);

        // when
        var response = createGroup_SameName(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(GroupException.NAME_ALREADY_EXISTS.httpStatus().value());
        assertThat(response.jsonPath().getString("message")).isEqualTo(
                GroupException.NAME_ALREADY_EXISTS.getErrorMessage());
    }

    private ExtractableResponse<Response> createGroup_SameName(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .multiPart("name", "codeSquad")
                .when()
                .post("/api/groups")
                .then().log().all()
                .extract();
    }

    @DisplayName("그룹 상세 조회에 성공한다.")
    @Test
    void retrieveGroupDetail_success() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group group = FixtureFactory.createGroup("codeSquad");
        Group save = groupRepository.save(group);
        Group find = groupRepository.findById(save.getId()).orElseThrow();

        // when
        var response = retrieveGroupDetail(find.getId(), member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo(find.getName());
        assertThat(response.jsonPath().getString("img")).isEqualTo(find.getImg());
    }

    private ExtractableResponse<Response> retrieveGroupDetail(Long groupId, Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .pathParam("groupId", groupId)
                .when()
                .get("/api/groups/{groupId}")
                .then().log().all()
                .extract();
    }

    @DisplayName("그룹 상세 조회에 없는 그룹 아이디를 주면 예외가 발생한다.")
    @Test
    void retrieveGroupDetail_fail_NotFound() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());

        // when
        var response = retrieveGroupDetail(0L, member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(GroupException.NOT_FOUND.httpStatus().value());
        assertThat(response.jsonPath().getString("message")).isEqualTo(GroupException.NOT_FOUND.getErrorMessage());
    }

    @DisplayName("내 그룹 추가에 성공한다.")
    @Test
    void addMyGroup_success() throws IOException {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group save = groupRepository.save(FixtureFactory.createGroup("codeSquad"));

        // when
        var response = addMyGroup(save.getId(), member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> addMyGroup(Long groupId, Long memberId) throws IOException {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .multiPart("image", File.createTempFile("add", "jpeg"), MediaType.IMAGE_JPEG_VALUE)
                .pathParam("groupId", groupId)
                .when()
                .post("/api/groups/{groupId}")
                .then().log().all()
                .extract();
    }

    @DisplayName("내 그룹에 있는 그룹을 추가하면 에러를 발생한다.")
    @Test
    void addMyGroup_fail_ExistMyGroup() throws IOException {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group group = groupRepository.save(FixtureFactory.createGroup("codeSquad"));
        memberGroupRepository.save(MemberGroup.of(member, group));

        // when
        var response = addMyGroup(group.getId(), member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(GroupException.MY_GROUP_ALREADY_EXISTS.httpStatus().value());
        assertThat(response.jsonPath().getString("message")).isEqualTo(
                GroupException.MY_GROUP_ALREADY_EXISTS.getErrorMessage());
    }

    @DisplayName("내 그룹 삭제에 성공한다.")
    @Test
    void deleteMyGroup_success() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group group = groupRepository.save(FixtureFactory.createGroup("codeSquad"));
        memberGroupRepository.save(MemberGroup.of(member, group));

        // when
        var response = deleteMyGroup(group.getId(), member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private ExtractableResponse<Response> deleteMyGroup(Long groupId, Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .pathParam("groupId", groupId)
                .when()
                .delete("/api/groups/{groupId}")
                .then().log().all()
                .extract();
    }

    @DisplayName("전체 그룹 목록 조회에 성공한다.")
    @Test
    void retrieveGroupList_All() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group group1 = groupRepository.save(FixtureFactory.createGroup("code"));
        Group group2 = groupRepository.save(FixtureFactory.createGroup("squad"));

        // when
        var response = retrieveGroupList_All(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("", Group.class).get(0).getId()).isEqualTo(group1.getId());
        assertThat(response.jsonPath().getList("", Group.class).get(1).getId()).isEqualTo(group2.getId());
    }

    private ExtractableResponse<Response> retrieveGroupList_All(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .when()
                .get("/api/groups")
                .then().log().all()
                .extract();
    }

    @DisplayName("전체 그룹 검색 조회에 성공한다.")
    @Test
    void retrieveGroupList_Word() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group group1 = groupRepository.save(FixtureFactory.createGroup("code"));
        groupRepository.save(FixtureFactory.createGroup("squad"));

        // when
        var response = retrieveGroupList_Word(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("", Group.class).get(0).getId()).isEqualTo(group1.getId());
    }

    private ExtractableResponse<Response> retrieveGroupList_Word(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .queryParam("word", "c")
                .when()
                .get("/api/groups")
                .then().log().all()
                .extract();
    }

    @DisplayName("내 그룹 목록 조회에 성공한다.")
    @Test
    void retrieveMyGroupList() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        groupRepository.save(FixtureFactory.createGroup("code"));
        Group group2 = groupRepository.save(FixtureFactory.createGroup("squad"));
        memberGroupRepository.save(MemberGroup.of(member, group2));

        // when
        var response = retrieveMyGroupList(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("", Group.class).get(0).getId()).isEqualTo(group2.getId());
    }

    private ExtractableResponse<Response> retrieveMyGroupList(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .queryParam("memberId", memberId)
                .when()
                .get("/api/groups")
                .then().log().all()
                .extract();
    }

    @DisplayName("내 그룹 목록 검색 조회에 성공한다.")
    @Test
    void retrieveMyGroupList_Word() {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group group1 = groupRepository.save(FixtureFactory.createGroup("code"));
        Group group2 = groupRepository.save(FixtureFactory.createGroup("squad"));
        memberGroupRepository.save(MemberGroup.of(member, group1));
        memberGroupRepository.save(MemberGroup.of(member, group2));

        // when
        var response = retrieveMyGroupList_Word(member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getList("", Group.class).get(0).getId()).isEqualTo(group2.getId());
    }

    private ExtractableResponse<Response> retrieveMyGroupList_Word(Long memberId) {
        return RestAssured
                .given().log().all()
                .header(HttpHeaders.AUTHORIZATION,
                        JWT_TOKEN_PREFIX + jwtProvider.createTokens(Map.of("memberId", memberId)).getAccessToken())
                .queryParam("memberId", memberId)
                .queryParam("word", "s")
                .when()
                .get("/api/groups")
                .then().log().all()
                .extract();
    }
}
