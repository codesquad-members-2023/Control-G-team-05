package com.codesquad.controlG.domain.token.service;

import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.token.entity.Token;
import com.codesquad.controlG.domain.token.repository.TokenRepository;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void save(String refreshToken, Member member) {
        Token token = Token.builder()
                .token(refreshToken)
                .member(member)
                .build();
        tokenRepository.save(token);
    }

    public void delete(Long memberId) {
        tokenRepository.deleteByMemberId(memberId);
    }

    public Token findByRefreshToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new CustomRuntimeException(JwtException.REFRESH_TOKEN_NOT_FOUND_EXCEPTION));
    }
}
