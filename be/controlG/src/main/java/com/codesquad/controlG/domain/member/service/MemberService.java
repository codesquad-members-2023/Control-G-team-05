package com.codesquad.controlG.domain.member.service;

import com.codesquad.controlG.domain.like.repository.LikeRepository;
import com.codesquad.controlG.domain.member.dto.MemberResponse;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    public MemberResponse readProfile(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomRuntimeException(MemberException.MEMBER_NOT_FOUND));
        long likeCount = likeRepository.countByLikedId(memberId);
        return MemberResponse.of(member, likeCount);
    }
}
