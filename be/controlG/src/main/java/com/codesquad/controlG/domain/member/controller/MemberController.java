package com.codesquad.controlG.domain.member.controller;

import com.codesquad.controlG.domain.auth.Auth;
import com.codesquad.controlG.domain.member.dto.LikedMemberResponse;
import com.codesquad.controlG.domain.member.dto.MemberResponse;
import com.codesquad.controlG.domain.member.dto.MemberUpdateRequest;
import com.codesquad.controlG.domain.member.service.MemberService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<MemberResponse> getProfile(@Auth Long memberId) {
        return ResponseEntity.ok(memberService.getProfile(memberId));
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<LikedMemberResponse>> getLikedProfiles(@Auth Long memberId,
                                                                      @RequestParam String selected) {
        return ResponseEntity.ok(memberService.getLikedProfiles(memberId, selected));
    }

    @PutMapping
    public ResponseEntity<Void> updateProfile(@Valid MemberUpdateRequest request,
                                              @Auth Long memberId) {
        memberService.update(request, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{likeId}/likes")
    public ResponseEntity<Void> like(@PathVariable Long likeId,
                                     @Auth Long memberId) {
        memberService.like(memberId, likeId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{blockId}/blocks")
    public ResponseEntity<Void> block(@PathVariable Long blockId,
                                      @Auth Long memberId) {
        memberService.block(memberId, blockId);
        return ResponseEntity.ok().build();
    }
}
