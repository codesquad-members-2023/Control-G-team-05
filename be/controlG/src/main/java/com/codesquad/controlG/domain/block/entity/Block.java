package com.codesquad.controlG.domain.block.entity;

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
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "blocker_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member blocker;

    @JoinColumn(name = "blocked_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member blocked;

    @Builder
    private Block(Long id, Member blocker, Member blocked) {
        this.id = id;
        this.blocker = blocker;
        this.blocked = blocked;
    }
}
