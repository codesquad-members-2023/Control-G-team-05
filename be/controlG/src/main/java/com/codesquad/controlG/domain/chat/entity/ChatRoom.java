package com.codesquad.controlG.domain.chat.entity;

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
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "group_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @JoinColumn(name = "member_id1")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member1;

    @JoinColumn(name = "member_id2")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member2;

    @Builder
    private ChatRoom(Long id, Group group, Member member1, Member member2) {
        this.id = id;
        this.group = group;
        this.member1 = member1;
        this.member2 = member2;
    }

    public static ChatRoom of(Group group, Member member1, Member member2) {
        return ChatRoom.builder()
                .group(group)
                .member1(member1)
                .member2(member2)
                .build();
    }

    public Long partnerId(Long senderId) {
        Long member1Id = this.member1.getId();
        Long member2Id = this.member2.getId();
        return senderId.equals(member1Id) ? member2Id : member1Id;
    }
}
