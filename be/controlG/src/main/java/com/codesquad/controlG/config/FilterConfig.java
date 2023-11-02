package com.codesquad.controlG.config;

import com.codesquad.controlG.domain.auth.AuthenticationContext;
import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.filter.CorsFilter;
import com.codesquad.controlG.filter.JwtFilter;
import com.codesquad.controlG.filter.SignupTokenFilter;
import com.codesquad.controlG.redis.util.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(JwtProvider jwtProvider, ObjectMapper objectMapper,
                                                       AuthenticationContext authenticationContext,
                                                       RedisUtil redisUtil) {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter(jwtProvider, objectMapper, authenticationContext, redisUtil));
        registrationBean.addUrlPatterns("/api/*"); // 필터를 적용할 URL 패턴 설정
        registrationBean.setOrder(2); // 필터 실행 순서를 설정 (숫자가 낮을수록 먼저 실행)
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<SignupTokenFilter> signupTokenFilter(JwtProvider jwtProvider,
                                                                       ObjectMapper objectMapper) {
        FilterRegistrationBean<SignupTokenFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SignupTokenFilter(jwtProvider, objectMapper));
        registrationBean.addUrlPatterns("/api/*"); // 필터를 적용할 URL 패턴 설정
        registrationBean.setOrder(3); // 필터 실행 순서를 지정
        return registrationBean;
    }

}
