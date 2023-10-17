package com.codesquad.controlG.exception.errorcode;

import org.springframework.http.HttpStatus;

public enum MemberException implements CustomException {

    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다.");

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
