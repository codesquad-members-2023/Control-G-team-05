package com.codesquad.controlG.domain.chat.repository.querydsl;

import com.codesquad.controlG.domain.chat.dto.response.ChatInfoMessages;
import com.codesquad.controlG.domain.chat.dto.response.ChatInfoPartner;
import java.util.List;

public interface ChatMessageQueryDslRepository {

    public void updateIsRead(Long chatRoomId, Long partnerId);

    public ChatInfoPartner getChatInfoPartner(Long chatRoomId, Long memberId);

    public List<ChatInfoMessages> getChatMessages(Long chatRoomId, Long memberId);
}
