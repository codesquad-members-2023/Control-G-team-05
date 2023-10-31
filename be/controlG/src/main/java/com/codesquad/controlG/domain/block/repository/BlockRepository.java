package com.codesquad.controlG.domain.block.repository;

import com.codesquad.controlG.domain.block.entity.Block;
import com.codesquad.controlG.domain.block.repository.querydsl.BlockQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long>, BlockQueryDslRepository {
}
