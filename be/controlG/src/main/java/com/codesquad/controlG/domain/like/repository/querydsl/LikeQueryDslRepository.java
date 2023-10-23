package com.codesquad.controlG.domain.like.repository.querydsl;

import static com.codesquad.controlG.domain.like.entity.QLike.like;

import com.codesquad.controlG.domain.like.entity.Like;
import com.codesquad.controlG.domain.member.entity.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class LikeQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public List<Member> findLikedMembers(Long likerId) {
        return queryFactory.select(like.liked)
                .from(like)
                .where(equalLikerId(likerId))
                .fetch();
    }

    private BooleanExpression equalLikerId(Long memberId) {
        return like.liker.id.eq(memberId);
    }

    public List<Long> findLikerIds(Long likedId) {
        return queryFactory.select(like.liker.id)
                .from(like)
                .where(equalLikedId(likedId))
                .fetch();
    }

    private BooleanExpression equalLikedId(Long memberId) {
        return like.liked.id.eq(memberId);
    }

    @Transactional
    public long deleteLikes(Long memberId1, Long memberId2) {
        return queryFactory.delete(like)
                .where(
                        (equalLikerId(memberId1).and(equalLikedId(memberId2)))
                        .or(equalLikerId(memberId2).and(equalLikedId(memberId1)))
                )
                .execute();
    }

    public boolean existsLike(Long likerId, Long likedId) {
        Like result = queryFactory.select(like)
                .from(like)
                .where(equalLikerId(likerId)
                        .and(equalLikedId(likedId)))
                .fetchFirst();

        return result!= null;
    }
}
