package com.codesquad.controlG.annotation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.amazonaws.services.s3.AmazonS3Client;
import com.codesquad.controlG.domain.auth.Oauth.OauthRequester;
import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.domain.auth.service.AuthService;
import com.codesquad.controlG.domain.group.repository.GroupRepository;
import com.codesquad.controlG.domain.image.ImageFile;
import com.codesquad.controlG.domain.image.ImageService;
import com.codesquad.controlG.domain.image.S3Uploader;
import com.codesquad.controlG.domain.like.repository.LikeRepository;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.domain.member_group.repository.MemberGroupRepository;
import com.codesquad.controlG.domain.token.repository.TokenRepository;
import com.codesquad.controlG.domain.token.service.TokenService;
import com.codesquad.controlG.redis.util.RedisUtil;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
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

    @MockBean
    protected S3Uploader s3Uploader;

}

