package com.codesquad.controlG.domain.auth.controller;

import static com.codesquad.controlG.filter.util.RequestParser.extractAccessToken;

import com.codesquad.controlG.domain.auth.Auth;
import com.codesquad.controlG.domain.auth.dto.request.AuthReissueTokenRequest;
import com.codesquad.controlG.domain.auth.dto.request.AuthSignUpRequest;
import com.codesquad.controlG.domain.auth.dto.response.AuthLoginResponse;
import com.codesquad.controlG.domain.auth.dto.response.AuthReissueTokenResponse;
import com.codesquad.controlG.domain.auth.service.AuthService;
import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/login/oauth/{provider}")
    public ResponseEntity<AuthLoginResponse> login(@PathVariable String provider, @RequestParam String code) {
        AuthLoginResponse loginResponse = authService.login(provider, code);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<AuthLoginResponse> signUp(@Valid AuthSignUpRequest request,
                                                    HttpServletRequest httpServletRequest) {
        Map<String, Object> map = (Map<String, Object>) httpServletRequest.getAttribute("infoMap");
        AuthLoginResponse loginResponse = authService.signUp(request, map);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<Map<String, String>> signOut(HttpServletRequest request, @Auth Long memberId) {
        String accessToken = extractAccessToken(request);
        authService.logout(accessToken, memberId);
        return ResponseEntity.ok(Collections.singletonMap("message", "로그아웃 성공"));
    }

    @PostMapping("/auth/access-token")
    public ResponseEntity<AuthReissueTokenResponse> reissueToken(
            @RequestBody AuthReissueTokenRequest authReissueTokenRequest) {
        return ResponseEntity.ok().body(authService.reissueToken(authReissueTokenRequest));
    }

    @GetMapping("/test")
    public String test(@Auth Long memberId, HttpServletRequest request) {
        return "멤버아이디: " + memberId + request.getAttribute("memberId");
    }
}
