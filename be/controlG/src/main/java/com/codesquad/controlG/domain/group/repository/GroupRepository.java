package com.codesquad.controlG.domain.group.repository;

import com.codesquad.controlG.domain.group.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long>, GroupRepositoryCustom {

    boolean existsByName(String name);
}
