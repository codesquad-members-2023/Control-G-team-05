package com.codesquad.controlG.domain.block.repository;

import com.codesquad.controlG.domain.block.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
}
