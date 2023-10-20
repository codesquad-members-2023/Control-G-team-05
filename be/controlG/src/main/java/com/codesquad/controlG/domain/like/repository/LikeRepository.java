package com.codesquad.controlG.domain.like.repository;

import com.codesquad.controlG.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Long> {

    long countByLikedId(Long likedId);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END FROM Like l WHERE l.liker.id = :likerId AND l.liked.id = :likedId")
    boolean existsByLikerIdAndLikedId(@Param("likerId") Long likerId, @Param("likedId") Long likedId);

    void deleteByLikerIdAndLikedId(Long likerId, Long likedId);

    Like saveByLikerIdAndLikedId(Long likerId, Long likedId);
}
