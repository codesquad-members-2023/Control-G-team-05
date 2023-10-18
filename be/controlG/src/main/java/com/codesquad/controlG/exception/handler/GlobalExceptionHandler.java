package com.codesquad.controlG.exception.handler;

import com.codesquad.controlG.exception.CustomRuntimeException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    public ResponseEntity<Map<String, String>> customExceptionHandler(CustomRuntimeException e) {
        log.info("api 예외발생! errorType: " + e.getCustomException().getName() + " errorMessage: " + e.getCustomException()
                .getErrorMessage());
        return e.sendError();
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Map<String, String>> maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.info("api 예외발생! errorType: " + e.getClass().getSimpleName() + " errorMessage: " + "최대 파일 업로드 용량 초과");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("errorType", e.getClass().getSimpleName(), "errorMessage",
                        "최대 파일 사이즈를 초과합니다."));
    }

}
