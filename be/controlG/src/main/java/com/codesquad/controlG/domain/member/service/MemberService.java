package com.codesquad.controlG.domain.member.service;

import com.codesquad.controlG.domain.block.repository.BlockRepository;
import com.codesquad.controlG.domain.image.ImageService;
import com.codesquad.controlG.domain.like.LikeStatus;
import com.codesquad.controlG.domain.like.repository.LikeRepository;
import com.codesquad.controlG.domain.like.repository.querydsl.LikeQueryDslRepository;
import com.codesquad.controlG.domain.member.dto.LikedMemberResponse;
import com.codesquad.controlG.domain.member.dto.MemberResponse;
import com.codesquad.controlG.domain.member.dto.MemberUpdateRequest;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.MemberException;
import java.util.List;
import java.util.stream.Collectors;
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
    private final BlockRepository blockRepository;
    private final LikeQueryDslRepository likeQueryDslRepository;

    public MemberResponse getProfile(Long memberId) {
        Member member = findMember(memberId);
        long likeCount = likeRepository.countByLikedId(memberId);
        return MemberResponse.of(member, likeCount);
    }

    public List<LikedMemberResponse> getLikedProfiles(Long memberId, String selected) {
        LikeStatus likeStatus = LikeStatus.from(selected);
        List<Member> likedMembers = likeQueryDslRepository.findLikedMembers(memberId);
        List<Long> likerIds = likeQueryDslRepository.findLikerIds(memberId);

        if (likeStatus == LikeStatus.like) {
            return getLike(likedMembers, likerIds);
        }
        return getMatched(likedMembers, likerIds);
    }

    private List<LikedMemberResponse> getLike(List<Member> memberList, List<Long> likerIds) {
        return memberList.stream()
                .filter(member -> !likerIds.contains(member.getId()))
                .map(member -> LikedMemberResponse.fromLike(member))
                .collect(Collectors.toList());
    }

    private List<LikedMemberResponse> getMatched(List<Member> memberList, List<Long> likerIds) {
        return memberList.stream()
                .filter(member -> likerIds.contains(member.getId()))
                .map(member -> LikedMemberResponse.fromMatched(member))
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(MemberUpdateRequest request, Long memberId) {
        Member member = findMember(memberId);
        if (isValidImage(request.getProfileImg())) {
            String profileImgUrl = imageService.uploadImage(request.getProfileImg());
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
        boolean isLikeExist = likeQueryDslRepository.existsLike(memberId, likedId);
        if (isLikeExist) {
            likeRepository.deleteByLikerIdAndLikedId(memberId, likedId);
            return;
        }
        likeRepository.saveByLikerIdAndLikedId(memberId, likedId);
    }

    @Transactional
    public void block(Long memberId, Long blockedId) {
        blockRepository.saveByBlockerIdAndBlockedId(memberId, blockedId);
        deleteLikes(memberId, blockedId);
    }

    private void deleteLikes(Long memberId1, Long memberId2) {
        likeQueryDslRepository.deleteLikes(memberId1, memberId2);
    }
}
