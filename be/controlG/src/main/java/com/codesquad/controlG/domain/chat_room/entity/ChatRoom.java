package com.codesquad.controlG.domain.chat_room.entity;

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

    @JoinColumn(name = "member1_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member1;

    @JoinColumn(name = "member2_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member2;

    @Builder
    private ChatRoom(Long id, Member member1, Member member2) {
        this.id = id;
        this.member1 = member1;
        this.member2 = member2;
    }
}
