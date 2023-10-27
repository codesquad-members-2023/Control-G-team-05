package com.codesquad.controlG.domain.chat.repository;

import com.codesquad.controlG.domain.chat.redis.RedisChatQueue;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RedisChatQueueRepository extends CrudRepository<RedisChatQueue, String> {

    List<RedisChatQueue> findByGroupId(Long groupId);
}
