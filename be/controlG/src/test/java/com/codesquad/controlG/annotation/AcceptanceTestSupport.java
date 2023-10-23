package com.codesquad.controlG.annotation;

import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.domain.group.repository.GroupRepository;
import com.codesquad.controlG.domain.like.repository.LikeRepository;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.domain.member_group.repository.MemberGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    protected LikeRepository likeRepository;
}
