package com.codesquad.controlG.filter;

import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.CustomException;
import com.codesquad.controlG.exception.errorcode.JwtException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public abstract class CommonFilter extends OncePerRequestFilter {

    // 메인화면, 회원가입
    private static final List<WhiteListUri> whiteListUris = List.of(
            new WhiteListUri("^/api/login/oauth/naver.*$", Set.of("POST")),
            new WhiteListUri("^/api/auth/access-token$", Set.of("POST")),
            new WhiteListUri("^/ws$", Set.of("GET"))
    );

    protected JwtProvider jwtProvider;
    protected ObjectMapper objectMapper;

    @Autowired
    public CommonFilter(JwtProvider jwtProvider, ObjectMapper objectMapper) {
        this.jwtProvider = jwtProvider;
        this.objectMapper = objectMapper;
    }

    protected boolean whiteListCheck(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod();

        return whiteListUris.stream()
                .anyMatch(entry -> entry.matches(uri, method));
    }

    protected boolean isSignupRequest(HttpServletRequest request) {
        return "/api/auth/signup".equals(request.getRequestURI());
    }

    protected void sendJwtExceptionResponse(ServletResponse response, RuntimeException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());

        CustomException jwtException = JwtException.from(e);
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        new CustomRuntimeException(jwtException).sendError().getBody()
                ));
    }
}

