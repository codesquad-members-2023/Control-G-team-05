package com.codesquad.controlG.domain.member.controller;

import com.codesquad.controlG.domain.auth.Auth;
import com.codesquad.controlG.domain.member.dto.MemberResponse;
import com.codesquad.controlG.domain.member.dto.MemberUpdateRequest;
import com.codesquad.controlG.domain.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<MemberResponse> getProfile(@Auth Long memberId) {
        return ResponseEntity.ok(memberService.getProfile(memberId));
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateProfile(@RequestPart(required = false) MultipartFile profileImg,
                                              @Valid @RequestPart MemberUpdateRequest request,
                                              @Auth Long memberId) {
        memberService.update(profileImg, request, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{likeId}/likes")
    public ResponseEntity<Void> like(@PathVariable Long likeId,
                                     @Auth Long memberId) {
        memberService.like(memberId, likeId);
        return ResponseEntity.ok().build();
    }
}
