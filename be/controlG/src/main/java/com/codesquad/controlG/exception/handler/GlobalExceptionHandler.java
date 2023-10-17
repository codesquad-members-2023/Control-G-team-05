package com.codesquad.controlG.exception.handler;

import com.codesquad.controlG.exception.CustomRuntimeException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<Map<String, String>> customExceptionHandler(CustomRuntimeException e) {
        log.info("api 예외발생! errorType: " + e.getCustomException().getName() + " errorMessage: " + e.getCustomException()
                .getErrorMessage());
        return e.sendError();
    }

}
