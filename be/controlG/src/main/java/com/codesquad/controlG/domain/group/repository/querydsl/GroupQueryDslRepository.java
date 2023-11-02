package com.codesquad.controlG.domain.group.repository.querydsl;

import com.codesquad.controlG.domain.group.entity.Group;
import java.util.List;

public interface GroupQueryDslRepository {

    List<Group> findList(String word, boolean forMember, Long memberId);
}
