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
public class ChatMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "chat_room_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ChatRoom chatRoom;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Long lastMessageId;

    private boolean isExit;

    @Builder
    private ChatMember(Long id, ChatRoom chatRoom, Member member, Long lastMessageId, boolean isExit) {
        this.id = id;
        this.chatRoom = chatRoom;
        this.member = member;
        this.lastMessageId = lastMessageId;
        this.isExit = isExit;
    }
}
