package com.codesquad.controlG.exception.errorcode;


import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoginMemberNotFoundException extends RuntimeException {
    private final HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
    private final String token;

    public LoginMemberNotFoundException(String token) {
        this.token = token;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getToken() {
        return token;
    }

    public ResponseEntity<MemberNotFoundResponse> sendError() {
        Map<String, String> responseMap = new LinkedHashMap<>();
        responseMap.put("signupToken", token);
        responseMap.put("error", "존재하지 않는 유저");
        MemberNotFoundResponse memberNotFoundResponse = new MemberNotFoundResponse(httpStatus, responseMap);
        return ResponseEntity.status(httpStatus)
                .body(memberNotFoundResponse);
    }
}
