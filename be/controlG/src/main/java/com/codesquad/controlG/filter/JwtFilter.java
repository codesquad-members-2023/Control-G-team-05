package com.codesquad.controlG.filter;

import static com.codesquad.controlG.filter.util.RequestParser.extractAccessToken;

import com.codesquad.controlG.domain.auth.AuthenticationContext;
import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.JwtException;
import com.codesquad.controlG.redis.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO
 * 로그아웃 구현시 레디스 블랙리스트 체크하는 필터 추가
 */
public class JwtFilter extends CommonFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String MEMBER_ID = "memberId";
    public static final String OPTIONS = "OPTIONS";

    AuthenticationContext authenticationContext;
    RedisUtil redisUtil;

    public JwtFilter(JwtProvider jwtProvider, ObjectMapper objectMapper, AuthenticationContext authenticationContext,
                     RedisUtil redisUtil) {
        super(jwtProvider, objectMapper);
        this.authenticationContext = authenticationContext;
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equals(OPTIONS)) {
            return;
        }

        if (whiteListCheck(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!isContainToken(request)) {
            sendJwtExceptionResponse(response, new CustomRuntimeException(JwtException.MISSING_HEADER_TOKEN));
            return;
        }

        if (isSignupRequest(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (redisUtil.hasKeyBlackList(extractAccessToken(request))) {
            sendJwtExceptionResponse(response, new CustomRuntimeException(JwtException.BLACKLISTED_JWT_EXCEPTION));
            return;
        }

        try {
            handleRequest(request);
            filterChain.doFilter(request, response);
        } catch (RuntimeException e) {
            sendJwtExceptionResponse(response, e);
        }

    }

    private void handleRequest(HttpServletRequest request) {
        String token = extractAccessToken(request);
        Claims claims = jwtProvider.getClaims(token);
        Object memberIdObj = claims.get(MEMBER_ID);
        if (memberIdObj == null) {
            throw new CustomRuntimeException(JwtException.MALFORMED_JWT_EXCEPTION);
        }
//        request.setAttribute(MEMBER_ID, memberIdObj);
        authenticationContext.setMemberId(claims);

    }

    private boolean isContainToken(HttpServletRequest request) {
        String authorization = request.getHeader(HEADER_AUTHORIZATION);
        return authorization != null && authorization.startsWith(TOKEN_PREFIX);
    }
}
