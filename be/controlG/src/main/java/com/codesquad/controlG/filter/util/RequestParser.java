package com.codesquad.controlG.filter.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

public class RequestParser {

    public static final String AUTHORIZATION = "Authorization";

    public static String extractAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring(7);
    }

    public static String extractAccessTokenFromAccessor(StompHeaderAccessor accessor) {
        String token = accessor.getFirstNativeHeader(AUTHORIZATION);
        return token.substring(7);
    }
}
