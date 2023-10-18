package com.codesquad.controlG.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface CustomException {

    String getName();

    HttpStatus httpStatus();

    String getErrorMessage();
}
