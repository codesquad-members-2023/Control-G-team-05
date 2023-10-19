package com.codesquad.controlG.domain.member.controller;

import com.codesquad.controlG.domain.auth.Auth;
import com.codesquad.controlG.domain.member.dto.MemberResponse;
import com.codesquad.controlG.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<MemberResponse> readProfile(@Auth Long memberId) {
        return ResponseEntity.ok(memberService.readProfile(memberId));
    }
}
