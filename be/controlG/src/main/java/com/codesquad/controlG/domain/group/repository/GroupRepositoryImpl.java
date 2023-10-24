package com.codesquad.controlG.domain.group.repository;

import static com.codesquad.controlG.domain.group.entity.QGroup.group;
import static com.codesquad.controlG.domain.member_group.entity.QMemberGroup.memberGroup;

import com.codesquad.controlG.domain.group.entity.Group;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GroupRepositoryImpl implements GroupRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Group> findList(String word, boolean forMember, Long memberId) {
        if (forMember) {
            return jpaQueryFactory.selectFrom(group)
                    .join(memberGroup)
                    .on(memberGroup.group.id.eq(group.id))
                    .where(memberGroup.member.id.eq(memberId),
                            containsWord(word))
                    .fetch();
        } else {
            return jpaQueryFactory.selectFrom(group)
                    .where(containsWord(word))
                    .fetch();
        }
    }

    private BooleanExpression containsWord(String word) {
        return word == null ? null : group.name.contains(word);
    }
}
