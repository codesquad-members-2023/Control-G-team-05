package com.codesquad.controlG.domain.auth.Oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserProfile {

    private String email;
    private String name;
    private String gender;
    private String birthYear;
    private String birthday;
}
