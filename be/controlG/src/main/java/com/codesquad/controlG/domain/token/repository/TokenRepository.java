package com.codesquad.controlG.domain.token.repository;

import com.codesquad.controlG.domain.token.entity.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    void deleteByMemberId(Long memberId);

    Optional<Token> findByToken(String token);
}
