package com.codesquad.controlG.domain.member_group.repository;

import com.codesquad.controlG.domain.member_group.entity.MemberGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberGroupRepository extends JpaRepository<MemberGroup, Long> {

    Long countByGroupId(Long groupId);

    boolean existsByMemberIdAndGroupId(Long memberId, Long groupId);

    void deleteByMemberIdAndGroupId(Long memberId, Long groupId);
}
