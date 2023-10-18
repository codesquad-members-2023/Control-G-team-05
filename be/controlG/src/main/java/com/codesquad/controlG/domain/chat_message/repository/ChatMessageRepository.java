package com.codesquad.controlG.domain.chat_message.repository;

import com.codesquad.controlG.domain.chat_message.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
