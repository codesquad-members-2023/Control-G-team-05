package com.codesquad.controlG.domain.group.repository;

import com.codesquad.controlG.domain.group.entity.Group;
import com.codesquad.controlG.domain.group.repository.querydsl.GroupQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupQueryDslRepository {

    boolean existsByName(String name);
}
