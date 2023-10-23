package com.codesquad.controlG.domain.auth.service;

import com.codesquad.controlG.domain.auth.Oauth.InMemoryProviderRepository;
import com.codesquad.controlG.domain.auth.Oauth.OauthProvider;
import com.codesquad.controlG.domain.auth.Oauth.OauthRequester;
import com.codesquad.controlG.domain.auth.Oauth.dto.OauthTokenResponse;
import com.codesquad.controlG.domain.auth.Oauth.dto.UserProfile;
import com.codesquad.controlG.domain.auth.dto.request.AuthReissueTokenRequest;
import com.codesquad.controlG.domain.auth.dto.request.AuthSignUpRequest;
import com.codesquad.controlG.domain.auth.dto.response.AuthLoginResponse;
import com.codesquad.controlG.domain.auth.dto.response.AuthReissueTokenResponse;
import com.codesquad.controlG.domain.auth.jwt.Jwt;
import com.codesquad.controlG.domain.auth.jwt.JwtProvider;
import com.codesquad.controlG.domain.image.ImageService;
import com.codesquad.controlG.domain.member.entity.Member;
import com.codesquad.controlG.domain.member.repository.MemberRepository;
import com.codesquad.controlG.domain.token.entity.Token;
import com.codesquad.controlG.domain.token.service.TokenService;
import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.LoginMemberNotFoundException;
import com.codesquad.controlG.exception.errorcode.MemberException;
import com.codesquad.controlG.redis.util.RedisUtil;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtProvider jwtProvider;
    private final InMemoryProviderRepository inMemoryProviderRepository;
    private final OauthRequester oauthRequester;
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final TokenService tokenService;
    private final RedisUtil redisUtil;


    @Transactional
    public AuthLoginResponse login(String providerName, String code) {
        OauthProvider provider = inMemoryProviderRepository.findByProviderName(providerName);
        OauthTokenResponse tokenResponse = oauthRequester.getToken(code, provider);
        UserProfile userProfile = oauthRequester.getUserProfile(providerName, tokenResponse, provider);
        Member member = memberRepository.findByEmail(userProfile.getEmail()).orElseGet(() -> {
            memberNotFoundResponse(userProfile);
            return null;
        });
        Jwt token = jwtProvider.createTokens(Map.of("memberId", member.getId()));
        tokenService.delete(member.getId());
        tokenService.save(token.getRefreshToken(), member);
        return AuthLoginResponse.of(token, member.getId());
    }

    private void memberNotFoundResponse(UserProfile userProfile) {
        String birthday = userProfile.getBirthYear() + "-" + userProfile.getBirthday();
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userProfile.getEmail());
        claims.put("name", userProfile.getName());
        claims.put("birthday", birthday);
        claims.put("gender", userProfile.getGender());
        Jwt jwt = jwtProvider.createSignUpToken(claims);
        throw new LoginMemberNotFoundException(jwt.getSignUpToken());
    }


    public AuthLoginResponse signUp(AuthSignUpRequest request, Map<String, Object> map) {
        MultipartFile image = request.getProfileImage();
        String imageUrl = imageService.uploadImage(image);
        validateEmail((String) map.get("email"));
        validateNickname(request.getNickname());
        Member member = AuthSignUpRequest.of(request, imageUrl, map);
        Long memberID = memberRepository.save(member).getId();
        Jwt token = jwtProvider.createTokens(Map.of("memberId", memberID));
        tokenService.save(token.getRefreshToken(), member);
        return AuthLoginResponse.of(token, member.getId());
    }

    private void validateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new CustomRuntimeException(MemberException.MEMBER_DUPLICATED_EMAIL);
        }
    }

    private void validateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new CustomRuntimeException(MemberException.MEMBER_DUPLICATED_NICKNAME);
        }
    }

    @Transactional
    public void logout(String accessToken, Long memberId) {
        setBlackList(accessToken);
        deleteRefreshToken(memberId);
    }

    private void setBlackList(String accessToken) {
        redisUtil.setBlackList(accessToken, "accessToken", 60);
    }

    private void deleteRefreshToken(Long memberId) {
        tokenService.delete(memberId);
    }

    public AuthReissueTokenResponse reissueToken(AuthReissueTokenRequest authReissueTokenRequest) {
        Token token = tokenService.findByRefreshToken(authReissueTokenRequest.getRefreshToken());
        String reissuedAccessToken = jwtProvider.reissueAccessToken(
                Collections.singletonMap("memberId", token.getMember().getId()));
        return AuthReissueTokenResponse.of(token, reissuedAccessToken);
    }

}
