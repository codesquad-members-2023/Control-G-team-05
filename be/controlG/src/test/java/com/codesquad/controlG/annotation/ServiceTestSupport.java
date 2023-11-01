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
