package com.codesquad.controlG.domain.like.repository.querydsl;

import com.codesquad.controlG.domain.member.entity.Member;
import java.util.List;

public interface LikeQueryDslRepository {

    List<Member> findLikedMembers(Long likerId);

    List<Long> findLikerIds(Long likedId);

    long deleteLikes(Long memberId1, Long memberId2);

    boolean existsLike(Long likerId, Long likedId);
}
