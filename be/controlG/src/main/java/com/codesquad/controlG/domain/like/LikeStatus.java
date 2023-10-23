package com.codesquad.controlG.domain.like;

import com.codesquad.controlG.exception.CustomRuntimeException;
import com.codesquad.controlG.exception.errorcode.LikeStatusException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LikeStatus {

    like, matched;

    public static LikeStatus from(String selected) {
        return Arrays.stream(LikeStatus.values())
                .filter(likeStatus -> likeStatus.name().equals(selected))
                .findFirst()
                .orElseThrow(() -> new CustomRuntimeException(LikeStatusException.INVALID_FILTER_VALUE));
    }
}
