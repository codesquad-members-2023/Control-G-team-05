package com.codesquad.controlG.filter;

import static com.codesquad.controlG.filter.util.RequestParser.extractAccessToken;

import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.JwtException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignupTokenFilter extends CommonFilter {

    public SignupTokenFilter(JwtProvider jwtProvider, ObjectMapper objectMapper) {
        super(jwtProvider, objectMapper);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (isSignupRequest(request)) {
            try {
                handleSignupRequest(request);
            } catch (RuntimeException e) {
                sendJwtExceptionResponse(response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void handleSignupRequest(HttpServletRequest request) {
        String token = extractAccessToken(request);
        Claims claims = jwtProvider.getClaimsFromSignUpToken(token);
        Object emailObj = claims.get("email");
        Object genderObj = claims.get("gender");
        Object birthdayObj = claims.get("birthday");
        Object nameObj = claims.get("name");
        if (emailObj == null) {
            throw new CustomRuntimeException(JwtException.MALFORMED_SIGN_UP_TOKEN);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("email", emailObj);
        map.put("gender", genderObj);
        map.put("birthday", birthdayObj);
        map.put("name", nameObj);
        request.setAttribute("infoMap", map);
    }
}
