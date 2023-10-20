package com.codesquad.controlG.domain.member.service;

import com.codesquad.controlG.domain.image.ImageService;
import com.codesquad.controlG.domain.like.entity.Like;
import com.codesquad.controlG.domain.like.repository.LikeRepository;
import com.codesquad.controlG.domain.member.dto.MemberResponse;
import com.codesquad.controlG.domain.member.dto.MemberUpdateRequest;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final ImageService imageService;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    public MemberResponse getProfile(Long memberId) {
        Member member = findMember(memberId);
        long likeCount = likeRepository.countByLikedId(memberId);
        return MemberResponse.of(member, likeCount);
    }

    @Transactional
    public void update(MultipartFile profileImg, MemberUpdateRequest request, Long memberId) {
        Member member = findMember(memberId);
        if (isValidImage(profileImg)) {
            String profileImgUrl = imageService.uploadImage(profileImg);
            member.changeProfileImg(profileImgUrl);
        }
        member.update(request);
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new CustomRuntimeException(MemberException.MEMBER_NOT_FOUND));
    }

    private boolean isValidImage(MultipartFile image) {
        return image != null && !image.isEmpty();
    }

    @Transactional
    public void like(Long memberId, Long likedId) {
        boolean isLikeExist = likeRepository.existsByLikerIdAndLikedId(memberId, likedId);
        if (isLikeExist) {
            likeRepository.deleteByLikerIdAndLikedId(memberId, likedId);
            return;
        }
        likeRepository.saveByLikerIdAndLikedId(memberId, likedId);
    }
}
