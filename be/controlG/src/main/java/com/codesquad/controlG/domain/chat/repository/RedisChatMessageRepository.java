package com.codesquad.controlG.domain.chat.repository;

import com.codesquad.controlG.domain.chat.redis.RedisChatMessage;
import org.springframework.data.repository.CrudRepository;

public interface RedisChatMessageRepository extends CrudRepository<RedisChatMessage, String> {
}
