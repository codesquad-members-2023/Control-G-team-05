package com.codesquad.controlG.domain.group.repository;

import com.codesquad.controlG.domain.group.entity.Group;
import java.util.List;

public interface GroupRepositoryCustom {

    List<Group> findList(String word, Long memberId);
}
