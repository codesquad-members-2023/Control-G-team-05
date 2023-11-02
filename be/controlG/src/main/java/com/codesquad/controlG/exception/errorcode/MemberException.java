package com.codesquad.controlG.exception.errorcode;

import org.springframework.http.HttpStatus;

public enum MemberException implements CustomException {

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    MEMBER_DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다."),
    MEMBER_DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    MemberException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
