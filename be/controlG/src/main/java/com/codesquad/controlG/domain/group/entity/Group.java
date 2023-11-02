package com.codesquad.controlG.domain.group.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Table(name = "`group`")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String img;

    @Builder
    private Group(Long id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public static Group of(String name, String img) {
        return Group.builder()
                .name(name)
                .img(img)
                .build();
    }
}
