package com.codesquad.controlG.domain.like.repository;

import com.codesquad.controlG.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
