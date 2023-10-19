package com.codesquad.controlG.exception.errorcode;

import org.springframework.http.HttpStatus;

public enum GroupException implements CustomException {
    NAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 그룹 이름입니다.");

    private final HttpStatus httpStatus;
    private final String message;

    GroupException(HttpStatus httpStatus, String message) {
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
