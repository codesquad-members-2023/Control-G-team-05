package com.codesquad.controlG.domain.chat.repository;

import com.codesquad.controlG.domain.chat.entity.ChatMessage;
import com.codesquad.controlG.domain.chat.repository.querydsl.ChatMessageQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>, ChatMessageQueryDslRepository {

    @Query("SELECT MAX(cm.id) FROM ChatMessage cm WHERE cm.chatRoom.id = :chatRoomId")
    Long findMaxIdByChatRoomId(@Param("chatRoomId") Long chatRoomId);

    void deleteByChatRoomId(Long chatRoomId);
}
