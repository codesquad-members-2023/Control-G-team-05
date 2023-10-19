package com.codesquad.controlG.domain.group.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import com.codesquad.controlG.annotation.AcceptanceTest;
import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.group.repository.GroupRepository;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.domain.member_group.entity.MemberGroup;
import com.codesquad.controlG.domain.member_group.repository.MemberGroupRepository;
import com.codesquad.controlG.exception.errorcode.GroupException;
import com.codesquad.controlG.fixture.FixtureFactory;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@AcceptanceTest
public class GroupAcceptanceTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberGroupRepository memberGroupRepository;

    @DisplayName("그룹을 등록한다.")
    @Test
    void createGroup_success() throws IOException {
        // when
        var response = createGroup();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> createGroup() throws IOException {
        return RestAssured
                .given().log().all()
                .multiPart("image", File.createTempFile("create", "jpeg"), MediaType.IMAGE_JPEG_VALUE)
                .multiPart("name", "codeSquad")
                .when()
                .post("/api/groups")
                .then().log().all()
                .extract();
    }

    @DisplayName("사진없이 그룹을 등록한다.")
    @Test
    void createGroup_success_NoImage() {
        // when
        var response = createGroup_NoImage();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> createGroup_NoImage() {
        return RestAssured
                .given().log().all()
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
        Group group = FixtureFactory.createGroup();
        groupRepository.save(group);

        // when
        var response = createGroup_SameName();

        // then
        assertThat(response.statusCode()).isEqualTo(GroupException.NAME_ALREADY_EXISTS.httpStatus().value());
        assertThat(response.jsonPath().getString("message")).isEqualTo(
                GroupException.NAME_ALREADY_EXISTS.getErrorMessage());
    }

    private ExtractableResponse<Response> createGroup_SameName() {
        return RestAssured
                .given().log().all()
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
        Group group = FixtureFactory.createGroup();
        Group save = groupRepository.save(group);
        Group find = groupRepository.findById(save.getId()).orElseThrow();

        // when
        var response = retrieveGroupDetail(find.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.jsonPath().getString("name")).isEqualTo(find.getName());
        assertThat(response.jsonPath().getString("img")).isEqualTo(find.getImg());
    }

    private ExtractableResponse<Response> retrieveGroupDetail(Long groupId) {
        return RestAssured
                .given().log().all()
                .pathParam("groupId", groupId)
                .when()
                .get("/api/groups/{groupId}")
                .then().log().all()
                .extract();
    }

    @DisplayName("그룹 상세 조회에 없는 그룹 아이디를 주면 예외가 발생한다.")
    @Test
    void retrieveGroupDetail_fail_NotFound() {
        // when
        var response = retrieveGroupDetail(0L);

        // then
        assertThat(response.statusCode()).isEqualTo(GroupException.NOT_FOUND.httpStatus().value());
        assertThat(response.jsonPath().getString("message")).isEqualTo(GroupException.NOT_FOUND.getErrorMessage());
    }

    @DisplayName("내 그룹 추가에 성공한다.")
    @Test
    void addMyGroup_success() throws IOException {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group save = groupRepository.save(FixtureFactory.createGroup());

        // when
        var response = addMyGroup(save.getId(), member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private ExtractableResponse<Response> addMyGroup(Long groupId, Long memberId) throws IOException {
        return RestAssured
                .given().log().all()
                .multiPart("image", File.createTempFile("add", "jpeg"), MediaType.IMAGE_JPEG_VALUE)
                .pathParam("groupId", groupId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + memberId)
                .when()
                .post("/api/groups/{groupId}")
                .then().log().all()
                .extract();
    }

    @DisplayName("내 그룹 추가에 성공한다.")
    @Test
    void addMyGroup_fail_ExistMyGroup() throws IOException {
        // given
        Member member = memberRepository.save(FixtureFactory.createMemberWiz());
        Group group = groupRepository.save(FixtureFactory.createGroup());
        memberGroupRepository.save(MemberGroup.of(member, group));

        // when
        var response = addMyGroup(group.getId(), member.getId());

        // then
        assertThat(response.statusCode()).isEqualTo(GroupException.MY_GROUP_ALREADY_EXISTS.httpStatus().value());
        assertThat(response.jsonPath().getString("message")).isEqualTo(
                GroupException.MY_GROUP_ALREADY_EXISTS.getErrorMessage());
    }
}
