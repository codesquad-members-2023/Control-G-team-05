package com.codesquad.controlG.domain.chat.repository.querydsl;

public interface ChatMessageQueryDslRepository {

    public void updateIsRead(Long chatRoomId);
}
