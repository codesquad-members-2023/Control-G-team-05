package com.codesquad.controlG.exception;

import com.codesquad.controlG.exception.errorcode.CustomException;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
public class CustomRuntimeException extends RuntimeException {

    public static final String STATUS = "status";
    public static final String MESSAGE = "message";

    private final CustomException customException;

    public ResponseEntity<Map<String, String>> sendError() {
        Map<String, String> responseMap = new LinkedHashMap<>();
        responseMap.put(STATUS, customException.httpStatus().toString());
        responseMap.put(MESSAGE, customException.getErrorMessage());

        return ResponseEntity.status(customException.httpStatus())
                .body(responseMap);
    }

}
