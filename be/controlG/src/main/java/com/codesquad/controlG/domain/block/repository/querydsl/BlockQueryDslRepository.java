package com.codesquad.controlG.domain.block.repository.querydsl;

import java.util.List;

public interface BlockQueryDslRepository {

    List<Long> findBlockedIds(Long blockerId);
}
