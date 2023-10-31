package com.codesquad.controlG.domain.chat.repository;

import com.codesquad.controlG.domain.chat.entity.ChatRoom;
import com.codesquad.controlG.domain.chat.repository.querydsl.ChatRoomQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>, ChatRoomQueryDslRepository {
}
