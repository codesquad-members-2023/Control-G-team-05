package com.codesquad.controlG.domain.like.repository;

import com.codesquad.controlG.domain.like.entity.Like;
import com.codesquad.controlG.domain.like.repository.querydsl.LikeQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LikeRepository extends JpaRepository<Like, Long>, LikeQueryDslRepository {

    long countByLikedId(Long likedId);

    void deleteByLikerIdAndLikedId(Long likerId, Long likedId);

}
