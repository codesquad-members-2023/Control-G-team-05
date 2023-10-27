package com.codesquad.controlG.domain.chat.repository;

import com.codesquad.controlG.domain.chat.entity.ChatMessage;
import com.codesquad.controlG.domain.chat.repository.querydsl.ChatMessageQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>, ChatMessageQueryDslRepository {
}
