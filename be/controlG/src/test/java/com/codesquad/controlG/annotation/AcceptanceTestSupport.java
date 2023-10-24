package com.codesquad.controlG.annotation;

import com.codesquad.controlG.domain.auth.Oauth.OauthRequester;
import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.domain.auth.service.AuthService;
import com.codesquad.controlG.domain.group.repository.GroupRepository;
import com.codesquad.controlG.domain.like.repository.LikeRepository;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.domain.member_group.repository.MemberGroupRepository;
import com.codesquad.controlG.domain.token.repository.TokenRepository;
import com.codesquad.controlG.domain.token.service.TokenService;
import com.codesquad.controlG.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;

@AcceptanceTest
public abstract class AcceptanceTestSupport {

    protected static final String JWT_TOKEN_PREFIX = "Bearer ";

    @Autowired
    protected JwtProvider jwtProvider;

    @Autowired
    protected GroupRepository groupRepository;

    @Autowired
    protected MemberRepository memberRepository;

    @Autowired
    protected MemberGroupRepository memberGroupRepository;

    @MockBean
    protected OauthRequester oauthRequester;

    @Autowired
    protected AuthService authService;

    @Autowired
    protected TokenService tokenService;

    @Autowired
    protected TokenRepository tokenRepository;

    @Autowired
    protected RedisUtil redisUtil;

    @Autowired
    protected RedisTemplate<String, Object> redisBlackListTemplate;
  
    @Autowired
    protected LikeRepository likeRepository;

    protected static final String TEST_EMAIL = "test@test.com";
    protected static final String TEST_GENDER = "M";
    protected static final String TEST_NAME = "라이트";
    protected static final String TEST_NICKNAME = "test";
    protected static final String TEST_BIRTHDAY = "06-18";
    protected static final String TEST_BIRTH_YEAR = "1998";

}

