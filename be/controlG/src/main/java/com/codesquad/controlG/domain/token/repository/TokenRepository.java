package com.codesquad.controlG.domain.token.repository;

import com.codesquad.controlG.domain.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
}
