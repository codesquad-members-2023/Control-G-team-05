package com.codesquad.controlG.domain.group.controller;

import com.codesquad.controlG.domain.auth.Auth;
import com.codesquad.controlG.domain.group.dto.GroupAddMineRequest;
import com.codesquad.controlG.domain.group.dto.GroupCreateRequest;
import com.codesquad.controlG.domain.group.dto.GroupDetailResponse;
import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.group.service.GroupService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/groups")
@RestController
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Void> createGroup(@Valid GroupCreateRequest groupCreateRequest) {
        groupService.create(groupCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{groupId}")
    public ResponseEntity<Void> addMyGroup(@PathVariable Long groupId, @Auth Long memberId,
                                           GroupAddMineRequest groupAddMineRequest) {
        groupService.addMyGroup(groupId, memberId, groupAddMineRequest.getImage());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupDetailResponse> retrieveGroupDetail(@PathVariable Long groupId) {
        GroupDetailResponse groupDetailResponse = groupService.retrieveGroupDetail(groupId);
        return ResponseEntity.ok().body(groupDetailResponse);
    }

    @GetMapping
    public ResponseEntity<List<Group>> retrieveGroupList(@RequestParam(required = false) String word,
                                                         @RequestParam(required = false) Long memberId) {
        List<Group> groupList = groupService.retrieveGroupList(word, memberId);
        return ResponseEntity.ok().body(groupList);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteMyGroup(@PathVariable Long groupId, @Auth Long memberId) {
        groupService.deleteMyGroup(groupId, memberId);
        return ResponseEntity.ok().build();
    }
}