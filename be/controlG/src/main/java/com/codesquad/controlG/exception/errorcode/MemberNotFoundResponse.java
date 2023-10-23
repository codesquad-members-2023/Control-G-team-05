package com.codesquad.controlG.exception.errorcode;

import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberNotFoundResponse {
    private HttpStatus status;
    private Map<String, String> message;

    public MemberNotFoundResponse(HttpStatus status, Map<String, String> message) {
        this.status = status;
        this.message = message;
    }
}
