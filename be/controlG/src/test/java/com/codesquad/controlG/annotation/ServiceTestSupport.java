package com.codesquad.controlG.annotation;

import com.codesquad.controlG.domain.auth.Oauth.InMemoryProviderRepository;
import com.codesquad.controlG.domain.auth.Oauth.OauthRequester;
import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.domain.auth.service.AuthService;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.domain.token.service.TokenService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ServiceTest
public class ServiceTestSupport {

    protected static final String TEST_EMAIL = "test@test.com";
    protected static final String TEST_GENDER = "M";
    protected static final String TEST_NAME = "라이트";
    protected static final String TEST_BIRTHDAY = "06-18";
    protected static final String TEST_BIRTH_YEAR = "1998";

    @Mock
    protected OauthRequester oauthRequester;

    @Mock
    protected MemberRepository memberRepository;

    @Mock
    protected JwtProvider jwtProvider;

    @Mock
    protected TokenService tokenService;

    @Mock
    protected InMemoryProviderRepository inMemoryProviderRepository;

    @InjectMocks
    protected AuthService authService;
}
