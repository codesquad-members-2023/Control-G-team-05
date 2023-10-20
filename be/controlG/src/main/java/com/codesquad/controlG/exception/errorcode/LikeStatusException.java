package com.codesquad.controlG.exception.errorcode;

import org.springframework.http.HttpStatus;

public enum LikeStatusException implements CustomException {
    INVALID_FILTER_VALUE(HttpStatus.BAD_REQUEST,"필터링 값으로는 'like'와 'matched'만 들어올 수 있습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    LikeStatusException(HttpStatus httpStatus, String message) {
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
