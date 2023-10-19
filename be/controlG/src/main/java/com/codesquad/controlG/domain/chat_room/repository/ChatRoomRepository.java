package com.codesquad.controlG.domain.chat_room.repository;

import com.codesquad.controlG.domain.chat_room.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
