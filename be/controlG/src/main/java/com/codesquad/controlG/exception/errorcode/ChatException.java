package com.codesquad.controlG.exception.errorcode;

import org.springframework.http.HttpStatus;

public enum ChatException implements CustomException {


    INVALID_CHAT_ROOM_ID(HttpStatus.BAD_REQUEST, "찾을수 없는 채팅방 아이디 입니다.");

    private final HttpStatus status;
    private final String message;

    ChatException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public HttpStatus httpStatus() {
        return status;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }

    @Override
    public String getName() {
        return name();
    }

}
