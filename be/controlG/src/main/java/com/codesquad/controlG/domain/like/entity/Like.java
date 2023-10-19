package com.codesquad.controlG.domain.like.entity;

import com.codesquad.controlG.domain.member.entity.Member;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "`like`")
@Entity
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "liker_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member liker;

    @JoinColumn(name = "liked_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member liked;

    @Builder
    private Like(Long id, Member liker, Member liked) {
        this.id = id;
        this.liker = liker;
        this.liked = liked;
    }
}
