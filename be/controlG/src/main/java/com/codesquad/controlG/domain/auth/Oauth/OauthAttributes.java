package com.codesquad.controlG.domain.auth.Oauth;

import com.codesquad.controlG.domain.auth.Oauth.dto.UserProfile;
import java.util.Arrays;
import java.util.Map;

public enum OauthAttributes {
    GOOGLE("google") {
        @Override
        public UserProfile of(Map<String, Object> attributes) {
            return UserProfile.builder()
                    .email((String) attributes.get("email"))
                    .build();
        }
    },
    NAVER("naver") {
        @Override
        public UserProfile of(Map<String, Object> attributes) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            return UserProfile.builder()
                    .email((String) response.get("email"))
                    .gender((String) response.get("gender"))
                    .birthday((String) response.get("birthday"))
                    .birthYear((String) response.get("birthyear"))
                    .name((String) response.get("name"))
                    .build();
        }
    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    public static UserProfile extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> providerName.equals(provider.providerName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of(attributes);
    }

    public abstract UserProfile of(Map<String, Object> attributes);
}
