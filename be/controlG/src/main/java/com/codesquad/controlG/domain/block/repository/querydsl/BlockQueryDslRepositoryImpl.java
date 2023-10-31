package com.codesquad.controlG.domain.block.repository.querydsl;

import static com.codesquad.controlG.domain.block.entity.QBlock.block;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BlockQueryDslRepositoryImpl implements BlockQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findBlockedIds(Long blockerId) {
        return queryFactory.select(block.blocked.id)
                .from(block)
                .where(equalBlockerId(blockerId))
                .fetch();
    }

    private BooleanExpression equalBlockerId(Long memberId) {
        return block.blocker.id.eq(memberId);
    }
}
