package com.codesquad.controlG.filter.util;

import javax.servlet.http.HttpServletRequest;

public class RequestParser {

    public static final String MEMBER_ID = "memberId";
    public static final String AUTHORIZATION = "Authorization";
    public static final String EMAIL = "email";

    public static String extractAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        return authorizationHeader.substring(7);
    }

    public static Long extractMemberId(HttpServletRequest request) {
        Object attribute = request.getAttribute(MEMBER_ID);
        if (attribute == null) {
            return null;
        }

        String memberId = attribute.toString();
        return Long.valueOf(memberId);
    }

    public static String extractEmail(HttpServletRequest request) {
        Object email = request.getAttribute(EMAIL);
        return email != null ? email.toString() : null;
    }

}
