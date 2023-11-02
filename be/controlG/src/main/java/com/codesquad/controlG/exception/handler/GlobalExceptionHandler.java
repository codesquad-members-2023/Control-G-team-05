package com.codesquad.controlG.exception.handler;

import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.LoginMemberNotFoundException;
import com.codesquad.controlG.exception.errorcode.MemberNotFoundResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
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

    @ExceptionHandler(LoginMemberNotFoundException.class)
    public ResponseEntity<MemberNotFoundResponse> LoginMemberNotFoundExceptionHandler(LoginMemberNotFoundException e) {
        log.info("api 예외발생! errorType: " + e.getClass().getSimpleName() + " errorMessage: " + "회원가입 토큰 발급");
        return e.sendError();
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, Object>> handleBindException(
            BindException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();

        String errorMessage = objectErrors.stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(","));

        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("status", HttpStatus.BAD_REQUEST.toString());
        responseMap.put("message", errorMessage.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(responseMap);
    }

}
