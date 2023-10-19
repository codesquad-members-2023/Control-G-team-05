package com.codesquad.controlG.exception.errorcode;

import org.springframework.http.HttpStatus;

public enum ImageException implements CustomException {

    UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다."),
    INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "이미지 파일의 확장자는 png, jpg, jpeg, svg만 가능합니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ImageException(HttpStatus httpStatus, String message) {
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