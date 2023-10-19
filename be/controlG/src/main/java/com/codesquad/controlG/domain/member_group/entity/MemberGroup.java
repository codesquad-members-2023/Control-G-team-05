package com.codesquad.controlG.domain.member_group.entity;

import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.member.entity.Member;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @Builder
    private MemberGroup(Long id, Member member, Group group) {
        this.id = id;
        this.member = member;
        this.group = group;
    }

    public static MemberGroup of(Member member, Group group) {
        return MemberGroup.builder()
                .member(member)
                .group(group)
                .build();
    }
}
