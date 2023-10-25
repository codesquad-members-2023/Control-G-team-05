package com.codesquad.controlG.domain.chat.entity;

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

    @JoinColumn(name = "member_id1")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member1;

    @JoinColumn(name = "member_id2")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member2;

    @Builder
    private ChatRoom(Long id, Member member1, Member member2) {
        this.id = id;
        this.member1 = member1;
        this.member2 = member2;
    }

    public Long findOpponentId(Member sender) {
        Long member1Id = this.member1.getId();
        Long member2Id = this.member2.getId();
        return sender.getId().equals(member1Id) ? member2Id : member1Id;
    }
}
