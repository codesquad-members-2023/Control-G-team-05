package com.codesquad.controlG.domain.chat.repository;

import com.codesquad.controlG.domain.chat.redis.RedisChatMember;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RedisChatMemberRepository extends CrudRepository<RedisChatMember, String> {

    Optional<RedisChatMember> findBySessionId(String sessionId);

    List<RedisChatMember> findByChatRoomId(Long chatRoomId);

    int countByChatRoomId(Long chatRoomId);
}
